package tair;

import org.springframework.beans.factory.annotation.Autowired;

import com.taobao.tair.Result;
import com.taobao.tair.ResultCode;
import com.taobao.tair.impl.mc.MultiClusterTairManager;

public class TairHandler {

	@Autowired
	private MultiClusterTairManager tairManager;

	public boolean addTair(String key, String value) {
		ResultCode rc = tairManager.put(10106, key, value);
		if (rc.isSuccess()) {
			return true;
		} else {
			return false;
		}
	}

	public String getTair(String key) {
		Result result = tairManager.get(10106, key);
		if (ResultCode.SUCCESS.equals(result.getRc())) {
			return result.getValue().toString();
		} else if (ResultCode.DATANOTEXSITS.equals(result.getRc())) {
			return "NO DATA";
		} else {
			return "Exception：" + result.getRc();
		}
	}
}
