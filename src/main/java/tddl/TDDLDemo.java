package tddl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.taobao.tddl.client.jdbc.TDataSource;

public class TDDLDemo {
	private TDataSource dataSource;

	public String connectVFS(String sql) throws SQLException {
		Connection conn = dataSource.getConnection();
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		String json = "";
		StringBuilder tempJson = new StringBuilder();
		tempJson.append("[");
		List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
		while (rs.next()) {
			Map<String, String> data = new HashMap<String, String>();
			String formatRecord = "{";
			int count = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= count; i++) {
				String key = rs.getMetaData().getColumnLabel(i);
				String val = rs.getObject(i) == null || rs.getObject(i).equals("")  ? "null" : rs.getObject(i).toString();
				data.put(key, val);
				formatRecord += "\"" + key + "\":\"" + val + "\",";
			}
			formatRecord = formatRecord.substring(0, formatRecord.lastIndexOf(",")) + "}";
			tempJson.append(formatRecord + ",");
			dataList.add(data);
		}

		if (tempJson.lastIndexOf(",") != -1) {
			json = tempJson.substring(0, tempJson.lastIndexOf(",")) + "]";
		}
		rs.close();
		ps.close();
		conn.close();
		System.out.println("jbm result:" + json);
		return json;
	}

	public void setDataSource(TDataSource dataSource) {
		this.dataSource = dataSource;
	}

//	 public static void main(String args[]) throws SQLException {
//	 //connectVFS("select * from vfs_dentry where user_id = 2280752072");
//	 connectVFS("select * from vfs_dentry where user_id = 3615054028 limit 2");
//	 }
}
