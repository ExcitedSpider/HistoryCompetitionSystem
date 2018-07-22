package qe.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@MappedJdbcTypes({JdbcType.VARCHAR})
public class ListTypeHandler extends BaseTypeHandler<List<String>> {
    private static Log log = LogFactory.getLog(ListTypeHandler.class);

    public void setNonNullParameter(PreparedStatement preparedStatement, int i, List<String> strings, JdbcType jdbcType) throws SQLException {
        //1.List集合转字符串
        StringBuffer sb = new StringBuffer();
        for (String string : strings) {
            sb.append(string).append(",");
        }
        //2.设置给ps
        preparedStatement.setString(i, sb.toString().substring(0, sb.toString().length() - 1));
        log.info("从List转换为String");
    }

    public List<String> getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String[] split = resultSet.getString(s).split(",");
        log.info("从String转换为List");
        return Arrays.asList(split);
    }

    public List<String> getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String[] split = resultSet.getString(i).split(",");
        log.info("从String转换为List");
        return Arrays.asList(split);
    }

    public List<String> getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String[] split = callableStatement.getString(i).split(",");
        log.info("从String转换为List");
        return Arrays.asList(split);
    }
}