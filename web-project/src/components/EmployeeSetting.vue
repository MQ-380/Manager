<template>
  <div id="PersonalSetting">
    <notice v-if="showError" :infoname="msg" @cancel="closeModal"></notice>
    <logout v-if="showLogout" @cancel="closeModal"></logout>
    <change v-if="showChangePass" :staffId="nowId" @closeModal="closeChange" @cancel="closeModal"></change>
    <success v-if="showSuccess" :msg="successMsg" @closeModal="closeSuccess"></success>
    <Form ref="StaffInfoForm" :model="StaffInfoForm" :label-width="80" :rules="staffValidate">
      <Form-item label="员工ID" prop="staid">
        <Input v-model="StaffInfoForm.staid" readonly/>
      </Form-item>
      <Form-item label="姓名" prop="name">
        <Input v-model="StaffInfoForm.name"/>
      </Form-item>
      <Form-item label="性别" prop="sex">
        <Radio-group v-model="StaffInfoForm.sex" style="float: left">
          <Radio label="男"></Radio>
          <Radio label="女"></Radio>
        </Radio-group>
      </Form-item>
      <Form-item label="email" prop="email">
        <Input v-model="StaffInfoForm.email"/>
      </Form-item>
      <Form-item label="联系电话" prop="telephone">
        <Input v-model="StaffInfoForm.telephone"/>
      </Form-item>
      <Form-item label="接收的培训" prop="training">
        <Input v-model="StaffInfoForm.training" type="textarea"/>
      </Form-item>
      <Form-item label="技能" prop="skill">
        <Input v-model="StaffInfoForm.skill" type="textarea"/>
      </Form-item>
    </Form>
    <div style="float: right">
      <Button type="primary" @click="toFresh('StaffInfoForm')">确定更新</Button>
      <Button type="ghost" @click="getStaffInformation">刷新信息</Button>
    </div>
    <br><br>
    <br><br>
    <Button type="warning" long @click="changePassword">修改密码</Button>
    <br><br>
    <Button type="error" long @click="logout">退出登录</Button>
  </div>
</template>

<script>
  import notice from './Notice'
  import logout from './LogoutNotice'
  import success from './Success'
  import change from './EmployeeChangePassword'
  export default {
    data () {
      return {
        StaffInfoForm: {},
        staffValidate: {
          name: [
            {required: true, message: '请填写员工姓名', trigger: 'blur'}
          ],
          email: [
            {type: 'email', message: '请使用正确的邮箱格式', trigger: 'blur'}
          ],
          phone: [
            {max: 13, message: '最多只能填写13位数字', trigger: 'blur'}
          ],
          training: [
            {max: 1000, message: '最多填写1000字', trigger: 'blur'}
          ],
          skill: [
            {max: 1000, message: '最多填写1000字', trigger: 'blur'}
          ]
        },
        showError: false,
        showLogout: false,
        showSuccess: false,
        showChangePass: false,
        successMsg: '',
        msg: '',
        nowId: ''
      }
    },
    created () {
      this.getStaffInformation()
    },
    watch: {
      '$route': 'CheckSignState'
    },
    components: {
      notice,
      logout,
      success,
      change
    },
    methods: {
      getStaffInformation () {
        this.$store.commit('GETLOGIN')
        let id = this.$store.state.LoginState.loginId
        this.$http({
          url: 'http://localhost:8081/consultPersonalInformation',
          params: {
            Id: id
          }
        }).then((response) => {
          let info = response.body
          if (info.hasOwnProperty('staff')) {
            this.StaffInfoForm = info.staff
            if (!this.StaffInfoForm.hasOwnProperty('sex')) {
              this.StaffInfoForm.sex = ''
            }
            if (!this.StaffInfoForm.hasOwnProperty('email')) {
              this.StaffInfoForm.email = ''
            }
            if (!this.StaffInfoForm.hasOwnProperty('training')) {
              this.StaffInfoForm.training = ''
            }
            if (!this.StaffInfoForm.hasOwnProperty('skill')) {
              this.StaffInfoForm.skills = ''
            }
            this.$store.commit('SETSTAFFINFO', this.StaffInfoForm)
          } else {
            this.msg = '无法获取员工信息'
            this.showError = true
          }
        }, (response) => {
          this.msg = '无法获得员工信息，请检查网络连接'
          this.showError = true
        })
      },
      logout () {
        this.showLogout = true
      },
      toFresh (item) {
        this.$refs[item].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.completedURL(),
              method: 'POST'
            }).then((response) => {
              if (response.body.status) {
                this.successMsg = '成功修改您的个人信息'
                this.showSuccess = true
              } else {
                this.msg = '无法更新您的个人信息'
                this.showError = true
              }
            }, (response) => {
              this.msg = '无法更新您的个人信息，请检查您的网络连接'
              this.showError = true
            })
          } else {
            this.$Message.error('表单验证不通过')
          }
        })
      },
      completedURL () {
        let url = 'http://localhost:8081/editPersonalInformation?'
        let items = this.StaffInfoForm
        let isFirst = true
        for (let prop in items) {
          if (!isFirst) {
            url += '&'
          }
          isFirst = false
          if (items[prop] === '') {
            url = url + 'staff.' + prop + '='
          } else {
            url = url + 'staff.' + prop + '=' + items[prop]
          }
        }
        return url
      },
      changePassword () {
        this.nowId = this.StaffInfoForm.staid
        this.showChangePass = true
      },
      closeModal () {
        this.showError = false
        this.showChangePass = false
        this.showLogOut = false
      },
      closeSuccess () {
        this.showSuccess = false
      },
      closeChange () {
        this.getStaffInformation()
        this.showChangePass = false
      }
    }
  }
</script>
