package com.kotlin.demo.model.model

/**
 * Author: SmartYu
 * EMail : luyu1235211@163.com
 * Date : 2019/5/8
 * Desc :
 */
data class LoginBean(
    val alia: Any,
    val companyId: Int,
    val nickName: String,
    val platForm: Int,
    val roles: List<Role>,
    val sessionId: String,
    val userId: Int,
    val userPhone: String
)

data class Role(
    val createId: Int,
    val createTime: String,
    val designation: String,
    val id: Int,
    val ifUseful: Int,
    val parentId: Int,
    val platform: Int,
    val roleName: String
)