package com.javaproject.record.Exporter;

import com.javaproject.record.Properties.MySQLProperties;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelExporter {
    @Autowired
    MySQLProperties mySQLProperties;

    public static void main(String[] args) {
        // 数据库连接配置
        String jdbcURL = "jdbc:mysql://localhost:3306/accounting_system?useSSL=false&serverTimezone=UTC&characterEncoding=utf8&allowPublicKeyRetrieval=true";
        String username = "root";  // 替换为你的数据库用户名
        String password = "1234";  // 替换为你的数据库密码

        // Excel文件路径
        String excelFilePath = "AccountingReport.xlsx";

        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password);
             Workbook workbook = new XSSFWorkbook();
             FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {

            // 创建工作表
            Sheet sheet = workbook.createSheet("会计报表");

            // 设置表头样式
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 12);

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

            // 创建主表头行
            Row headerRow = sheet.createRow(0);
            String[] mainColumns = {"统计类型", "分类/日期", "金额", "详细信息"};
            for (int i = 0; i < mainColumns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(mainColumns[i]);
                cell.setCellStyle(headerCellStyle);
            }

            // 合并单元格
            sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, 0, 0, 3));
            Cell mergeCell = headerRow.createCell(0);
            mergeCell.setCellValue("会计报表统计");
            mergeCell.setCellStyle(headerCellStyle);

            // 添加分隔行
            Row separatorRow = sheet.createRow(1);
            for (int i = 0; i < 4; i++) {
                Cell cell = separatorRow.createCell(i);
                cell.setCellStyle(headerCellStyle);
                CellStyle style = workbook.createCellStyle();
                style.setBorderTop(BorderStyle.THIN);
                cell.setCellStyle(style);
            }

            // 按类型统计（收入/支出）
            int startRow = 3;
            createTypeStatistics(connection, sheet, startRow, headerCellStyle);
            startRow += getRowCount(sheet, startRow) + 2;  // 跳过类型统计部分

            // 添加分隔线
            Row typeSeparator = sheet.createRow(startRow++);
            for (int i = 0; i < 4; i++) {
                Cell cell = typeSeparator.createCell(i);
                if (i == 0) {
                    cell.setCellValue("----------------------------");
                } else {
                    cell.setCellValue("");
                }
            }

            // 按日期统计
            createDateStatistics(connection, sheet, startRow, headerCellStyle);

            // 自动调整列宽
            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            // 写入文件
            workbook.write(fileOut);
            System.out.println("Excel报表已成功导出到: " + excelFilePath);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void createTypeStatistics(Connection connection, Sheet sheet, int startRow, CellStyle headerCellStyle) throws SQLException {
        // 创建类型统计表头
        Row typeHeaderRow = sheet.createRow(startRow++);
        String[] typeColumns = {"类型", "总金额"};
        for (int i = 0; i < typeColumns.length; i++) {
            Cell cell = typeHeaderRow.createCell(i);
            cell.setCellValue(typeColumns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // 查询类型统计数据
        String typeQuery = "SELECT " +
                "CASE WHEN c.category_type = 0 THEN '收入' ELSE '支出' END AS type, " +
                "SUM(CASE WHEN c.category_type = 0 THEN t.amount ELSE -t.amount END) AS total_amount " +
                "FROM transaction t " +
                "JOIN category c ON t.category_id = c.category_id " +
                "GROUP BY c.category_type";

        try (PreparedStatement typeStatement = connection.prepareStatement(typeQuery);
             ResultSet typeResultSet = typeStatement.executeQuery()) {

            int rowNum = startRow;
            while (typeResultSet.next()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(typeResultSet.getString("type"));
                row.createCell(1).setCellValue(typeResultSet.getInt("total_amount"));

                // 添加详细信息列（留空）
                row.createCell(2).setCellValue("");
                row.createCell(3).setCellValue("");
            }

            // 添加类型统计小计
            if (rowNum > startRow) {
                Row subtotalRow = sheet.createRow(rowNum++);
                subtotalRow.createCell(0).setCellValue("类型小计");
                subtotalRow.createCell(1).setCellValue(calculateTypeSubtotal(connection));
                // 详细信息列留空
                subtotalRow.createCell(2).setCellValue("");
                subtotalRow.createCell(3).setCellValue("");
            }
        }
    }

    private static int calculateTypeSubtotal(Connection connection) throws SQLException {
        String query = "SELECT SUM(CASE WHEN c.category_type = 0 THEN t.amount ELSE -t.amount END) AS total " +
                "FROM transaction t JOIN category c ON t.category_id = c.category_id";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
        }
        return 0;
    }

    private static void createDateStatistics(Connection connection, Sheet sheet, int startRow, CellStyle headerCellStyle) throws SQLException {
        // 创建日期统计表头
        Row dateHeaderRow = sheet.createRow(startRow++);
        String[] dateColumns = {"日期", "净收入/支出"};
        for (int i = 0; i < dateColumns.length; i++) {
            Cell cell = dateHeaderRow.createCell(i);
            cell.setCellValue(dateColumns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // 查询日期统计数据
        String dateQuery = "SELECT " +
                "DATE(t.transaction_time) AS transaction_date, " +
                "SUM(CASE WHEN c.category_type = 0 THEN t.amount ELSE -t.amount END) AS daily_total " +
                "FROM transaction t " +
                "JOIN category c ON t.category_id = c.category_id " +
                "GROUP BY DATE(t.transaction_time) " +
                "ORDER BY transaction_date";

        try (PreparedStatement dateStatement = connection.prepareStatement(dateQuery);
             ResultSet dateResultSet = dateStatement.executeQuery()) {

            int rowNum = startRow;
            while (dateResultSet.next()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(dateResultSet.getString("transaction_date"));
                row.createCell(1).setCellValue(dateResultSet.getInt("daily_total"));

                // 添加详细信息列（留空）
                row.createCell(2).setCellValue("");
                row.createCell(3).setCellValue("");
            }

            // 添加日期统计总计
            if (rowNum > startRow) {
                Row totalRow = sheet.createRow(rowNum++);
                totalRow.createCell(0).setCellValue("总计");
                totalRow.createCell(1).setCellValue(calculateDateTotal(connection));
                // 详细信息列留空
                totalRow.createCell(2).setCellValue("");
                totalRow.createCell(3).setCellValue("");
            }
        }
    }

    private static int calculateDateTotal(Connection connection) throws SQLException {
        String query = "SELECT SUM(CASE WHEN c.category_type = 0 THEN t.amount ELSE -t.amount END) AS total " +
                "FROM transaction t JOIN category c ON t.category_id = c.category_id";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("total");
            }
        }
        return 0;
    }

    private static int getRowCount(Sheet sheet, int startRow) {
        int count = 0;
        for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
            if (sheet.getRow(i) != null) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}