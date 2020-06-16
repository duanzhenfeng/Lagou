package com.lg.dzf.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {

	//解析接口状态
	private String code;
	//解析提示文本
	private String msg;
	//解析数据长度
	private Long count;
	//解析数据列表
	private List<T> data;

}