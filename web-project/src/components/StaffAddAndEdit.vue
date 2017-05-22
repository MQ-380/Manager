<template>
  <div id="StaffAddEdit">
    <success v-if="showSuccess" :msg="successMsg" @closeModal="closeSuccess"></success>
    <notice v-if="showNotice" :infoname="noticeMsg" @closeModal="closeNotice"></notice>
    <Modal v-model="showAdd" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>{{headname}}</span>
      </p>
      <div style="text-align:center">
        <Form ref="staffFormItem" :model="staffFormItem" :label-width="80" :rules="staffValidate">
          <Form-item label="姓名" prop="name">
            <Input v-model="staffFormItem.name" placeholder="请输入"/>
          </Form-item>
          <Form-item label="性别" prop="sex">
            <Radio-group v-model="staffFormItem.sex" style="float: left">
              <Radio label="男"></Radio>
              <Radio label="女"></Radio>
            </Radio-group>
          </Form-item>
          <Form-item label="e-mail" prop="email">
            <Input v-model="staffFormItem.email"/>
          </Form-item>
          <Form-item label="联系电话" prop="phone">
            <Input v-model="staffFormItem.phone"/>
          </Form-item>
          <Form-item label="入职时间" prop="time">
            <Date-picker v-model="staffFormItem.time" type="date" placeholder="请选择日期"></Date-picker>
          </Form-item>
          <Form-item label="级别" prop="rank">
            <Select v-model="staffFormItem.rank">
              <Option v-for="item in allrank" :key="item" :value="item.rank">{{item.name}}</Option>
            </Select>
          </Form-item>
          <Form-item label="部门" prop="deid">
            <Input v-model="departmentname" readonly/>
          </Form-item>
          <Form-item label="接受的培训" prop="training">
            <Input v-model="staffFormItem.training" type="textarea" :rows="4"/>
          </Form-item>
          <Form-item label="技能" prop="skill">
            <Input v-model="staffFormItem.skill" type="textarea" :rows="4"/>
          </Form-item>
        </Form>
      </div>
      <div slot="footer">
        <Button type="primary" @click="confirmToAdd('staffFormItem')">确定</Button>
        <Button type="ghost" @click="confirmToCancel()">取消</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  import success from './Success.vue'
  import notice from './Notice'
  export default {
    data () {
      const TimeValidate = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请选择入职时间'))
        } else {
          callback()
        }
      }

      const RankValidate = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请选择级别'))
        } else {
          callback()
        }
      }

      return {
        headname: this.Info.headname,
        showAdd: true,
        showSuccess: false,
        showNotice: false,
        noticeMsg: '',
        successMsg: '',
        staffFormItem: {
          name: this.Info.name,
          sex: this.Info.sex,
          email: this.Info.email,
          phone: this.Info.phone,
          time: this.Info.time,
          rank: this.Info.rank,
          deid: this.Info.deid,
          training: this.Info.training,
          skill: this.Info.skill
        },
        departmentname: this.Info.departmentname,
        staffValidate: {
          name: [
            {required: true, message: '请填写员工姓名', trigger: 'blur'}
          ],
          sex: [
            {required: true, message: '请选择员工性别', trigger: 'blur'}
          ],
          email: [
            {type: 'email', message: '请使用正确的邮箱格式', trigger: 'blur'}
          ],
          phone: [
            {max: 13, message: '最多只能填写13位数字', trigger: 'blur'}
          ],
          time: [
            {required: true, validator: TimeValidate, trigger: 'blur'}
          ],
          rank: [
            {required: true, validator: RankValidate, trigger: 'blur'}
          ],
          training: [
            {max: 1000, message: '最多填写1000字', trigger: 'blur'}
          ],
          skill: [
            {max: 1000, message: '最多填写1000字', trigger: 'blur'}
          ]
        },
        allrank: [
          {
            rank: 3,
            name: '总经理'
          },
          {
            rank: 2,
            name: '部门经理'
          },
          {
            rank: 1,
            name: '员工'
          }
        ]
      }
    },
    props: ['Info'],
    components: {
      success,
      notice
    },
    methods: {
      confirmToAdd (item) {
        let type = this.Info.type
        this.$refs[item].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.completeURL(),
              method: 'POST'
            }).then((response) => {
              if (response.body.status) {
                if (type === 'add') {
                  this.successMsg = '成功添加员工'
                } else {
                  this.successMsg = '成功修改员工信息'
                }
                this.showSuccess = true
              } else {
                if (type === 'add') {
                  this.noticeMsg = '添加员工失败，请检查提供的信息'
                } else {
                  this.noticeMsg = '修改员工信息失败，请检查提供的信息'
                }
                this.showNotice = true
              }
            }, (response) => {
              this.noticeMsg = '无法添加员工或修改员工资料，请检查网络连接'
              this.showNotice = true
            })
          } else {
            this.$Message.error('表单验证不通过')
          }
        })
      },
      confirmToCancel () {
        this.$emit('cancel')
      },
      closeSuccess () {
        this.showSuccess = false
        this.$emit('closeModal')
      },
      closeNotice () {
        this.showNotice = false
      },
      completeURL () {
        let basicurl
        let info = this.staffFormItem
        let first = true
        if (this.Info.type === 'add') {
          basicurl = 'http://localhost:8081/addStaff?'
          for (let prop in info) {
            if (info[prop] !== '') {
              if (!first) {
                basicurl += '&'
              }
              first = false
              if (prop === 'time') {
                basicurl = basicurl + 'staff.' + prop + '=' + info[prop].toLocaleDateString()
              } else {
                basicurl = basicurl + 'staff.' + prop + '=' + info[prop]
              }
            }
          }
        } else {
          basicurl = 'http://localhost:8081/editStaffInformation?staff.staid=' + this.Info.staid
          for (let prop in info) {
            basicurl += '&'
            if (prop === 'time') {
              basicurl = basicurl + 'staff.' + prop + '=' + info[prop].toLocaleDateString()
            } else {
              if (info[prop] === undefined) {
                basicurl = basicurl + 'staff.' + prop + '='
              } else {
                basicurl = basicurl + 'staff.' + prop + '=' + info[prop]
              }
            }
          }
        }
        return basicurl
      }
    }
  }
</script>
