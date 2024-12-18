package com.hanson.java.base.mysqllock.impl;

import java.sql.*;

/**
 * @author hanson.huang
 * @version V1.0
 * @ClassName PessimisticLockExample
 * @date 2024/12/18 18:07
 **/
public class PessimisticLockExample {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/csdn-test";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;

        try {
            // 1. 获取数据库连接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 2. 关闭自动提交，开启事务
            conn.setAutoCommit(false);

            // 3. 使用悲观锁锁定要更新的行
            String selectSQL = "SELECT balance FROM accounts WHERE id = ? FOR UPDATE";
            pstmt1 = conn.prepareStatement(selectSQL);
            pstmt1.setInt(1, 1); // 假设我们要更新ID为1的账户
            rs = pstmt1.executeQuery();

            // 4. 检查行是否存在并读取当前余额
            if (rs.next()) {
                double currentBalance = rs.getDouble("balance");
                System.out.println("Current Balance: " + currentBalance);

                // 5. 计算新余额（例如，增加100）
                double newBalance = currentBalance + 100;

                // 6. 更新余额
                String updateSQL = "UPDATE accounts SET balance = ? WHERE id = ?";
                pstmt2 = conn.prepareStatement(updateSQL);
                pstmt2.setDouble(1, newBalance);
                pstmt2.setInt(2, 1);
                pstmt2.executeUpdate();

                // 7. 提交事务
                conn.commit();
                System.out.println("Balance updated successfully.");
            } else {
                // 回滚事务，因为没有找到要更新的行
                conn.rollback();
                System.out.println("No account found with the specified ID.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    // 发生异常时回滚事务
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 8. 关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmt1 != null) pstmt1.close();
                if (pstmt2 != null) pstmt2.close();
                if (conn != null) conn.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}