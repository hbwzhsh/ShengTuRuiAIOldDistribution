package com.model;

import java.util.List;

public class IntendType {
		private String intendName;
		private List<String> deviceIds;
		private String cmd;

		public IntendType(){};
		
		public IntendType(String intendName, List<String> deviceIds, String cmd) {
			super();
			this.intendName = intendName;
			this.deviceIds = deviceIds;
			this.cmd = cmd;
		}

		public String getIntendName() {
			return intendName;
		}

		public void setIntendName(String intendName) {
			this.intendName = intendName;
		}

		public List<String> getDeviceIds() {
			return deviceIds;
		}

		public void setDeviceIds(List<String> deviceIds) {
			this.deviceIds = deviceIds;
		}

		public String getCmd() {
			return cmd;
		}

		public void setCmd(String cmd) {
			this.cmd = cmd;
		}
	}