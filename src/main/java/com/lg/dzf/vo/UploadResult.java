package com.lg.dzf.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//生成get,set,toSting,equals()方法
@Data
//生成一个包含所有变量构造函数
@AllArgsConstructor
//生成一个没有变量的构造函数
@NoArgsConstructor
public class UploadResult {
	private String code;
	private String msg;
	private Object data;
}