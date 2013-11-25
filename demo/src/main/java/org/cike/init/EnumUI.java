package org.cike.init;

import org.cike.ui.MyEasyUI;

public enum EnumUI {
	EASYUI {
		@Override
		public Class getUI() {
			// TODO Auto-generated method stub
			return MyEasyUI.class;
		}
	},
	BOOTSTRAP

	{
		@Override
		public Class getUI() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	public abstract Class getUI();
}
