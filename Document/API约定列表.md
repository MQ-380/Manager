# API约定列表
以前后端更好的合作为目的，特制订本文档，约定所有的交互API。

V1.0 增加所有API

## 说明
1. 内容的顺序为 调用名称，调用参数，返回类型。第一个为样式，后同。

2. 统一约定： 和时间有关的项时间格式为YYYY— MM— DD，具体：HH:MM:SS

## 登录操作 
* 调用名称：userLogin
* 调用参数：String userName, String password
* 返回类型：  JSON
  {"status”:”1”, “type”:”X”,“id”:”X” }  
 或  {“status”:”0”, “,msg” : “用户名或密码错误” }
 status：0  -> 登录未成功       1 -> 登录成功
  type: 0 -> 管理员   1 -> 普通员工   2 -> 部门经理   3 -> 总经理     
  id: 登录成功账号的id
  msg: 需要提示的语句

## 部门查询
* consultDepartment
* NULL
* JSON
{“department”: [
 { “ID” : “XX”,“name” : “xx”},
…
 ] }
ID: 部门ID ,name：部门名称

## 增加部门
* addDepartment
* String departmentname
* String  true ->  添加成功 false -> 添加失败

## 删除部门
* deleteDepartment
* String[] departmentID
* String   true ->  删除成功 false -> 删除失败

## 修改部门
* editDepartment
* JSON newInformation:
{“id”: XX,”name”:”XX”}
* String rue ->  修改成功 false -> 修改失败

## 员工查询
* consultAllStaff
* String departmentId;
* JSON
{“staff”: [
 { “ID” : “XX”,“name” : “xx”},
…
 ] }

## 个人信息查询
* consultStaffInformation
* String id
* JSON
{“name”:”XX”, “sex”:”X”, “email”:”XXX”,”tele”:”XX”,”jointime”:”XX”,”degree”:”XX”,”department”:”XX”,
“learning”:”XX”,”skill”:”xx”}

jointime: 加入时间 learning：参加培训经历  skill：技能

## 个人信息修改
* editStaffInformation
* JSON  格式同上条
* String rue ->  修改成功 false -> 修改失败

## 个人信息新增
* addStaff
* JSON格式同上
* String  true ->  添加成功 false -> 添加失败

## 个人信息删除
* deleteStaff
* String[] staffID
* String   true ->  删除成功 false -> 删除失败

## 个人信息查询
* consultPersonalInformation
* String Id
* JSON
{“name”:”XX”, “sex”:”X”, “email”:”XXX”,”tele”:”XX”,
“learning”:”XX”,”skill”:”xx”}

## 个人信息修改
* editPersonalInformation
* 格式同上
* String rue ->  修改成功 false -> 修改失败

## 个人密码修改
* editPassword
* String id,String password
* String rue ->  修改成功 false -> 修改失败

## 签到状态查询
* loginStatus 
* String id
* String :
	  true: 只能签离，不能签到
          false：只能签到，不能签离
          all：不能签到，不能签离

## 签到
* Login
* String id
* String : true -> 签到成功 false -> 签到失败

## 签离
* Logout
* String id
* String : true -> 签离成功 false -> 签离失败

## 签到时间查询
* consultLogData
* JSON：{“id”: “xx”,  “start”: “XX”, “end”:”XX”}
* JSON:  
{“data”:
	[
	 {“date”:”XXX”,”start”: “XXX”: “end”: “XXX”}
	…. 
	]
}

## 外出申请查询
* consultAllApply
*  JSON：{“id”: “xx”,  “start”: “XX”, “end”:”XX”}
*  JSON:  
{“data”:
	[
	 {“status”:”XX”, “applyId”:”XX”, “start”:”XX”, ”end”:”XXX”, “type”:”XX“,
	  “issue”: “xxx”, “operator”:”XXX”
	},
	…. 
	]
}

## 新建申请
* addNewLeavingApply
* JSON: {“id”:”XXX” “start”:”XX”, ”end”:”XXX”, “type”:”XX“,
	  “issue”: “xxx”}
* String  true ->  添加成功 false -> 添加失败

## 撤销外出申请
* deleteApply
* JSON: {“StaffId”:”XXX”,”ApplyId”:”XXX”}
* String  true ->  撤销成功 false -> 撤销失败

## 工资查询
* consultSalary
* JSON: {“id”: “xx”,  “start”: “XX”, “end”:”XX”}
* JSON:
{ “data”:[
{“date”:”XXX”, “total”:”xx“, “basic”: “XX”, 
	“signPunishTotal” : “XXX”,
	“signPunish”: [{
				“time”:”xxx”, “amount”:”XXX”, “reason”: “XXX”
				},…..],
	“BounsTotal” :”XXX”,
	“Bouns”: [{
				“time”:”xxx”, “amount”:”XXX”, inOut:”XXX”,    
                                “reason”: “XXX”,”operator”:”XXX”
				},…..],
},
….
]
}
bonus里
0 in 1 out

## 添加奖惩
* addBounsPunish
* JSON:
{“id”:”XX”,”type”:”XXX”,”amount” :”XXX”,”reason”:”XXX”,operatorID:”XXX”}
* String  true ->  添加成功 false -> 添加失败

## 查询外出申请（经理版）
* consultAllApply
*  JSON：{“manageid”: “xx”,  “start”: “XX”, “end”:”XX”}
*  JSON:  
{“data”:
	[
	 {“status”:”XX”, “applyId”:”XX”,”staffId”:”XXX”, “start”:”XX”, ”end”:”XXX”, “type”:”XX“,
	  “issue”: “xxx”
	},
	…. 
	]
}

## 确认外出申请
* confirmApply
* JSON: {“managerId”:”XXX”,”ApplyId”:”XXX”}
* String  true ->  确认成功 false -> 确认失败





