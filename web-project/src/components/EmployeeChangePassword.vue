<template>
  <div id="changePassword">
    <success v-if="showSuccess" :msg="successMsg" @closeModal="closeSuccess"></success>
    <notice v-if="showError" :infoname="errorMsg" @closeModal="closeError"></notice>
    <Modal v-model="showEdit">
      <p slot="header" style="color:#843534;text-align:left">
        <span>修改密码</span>
      </p>
      <Form ref="passwordInfo" :model="passwordInfo" :label-width="80" :rules="passwordValidate">
        <Form-item label='新密码' prop="newPass">
          <Input v-model="passwordInfo.newPass" type="password"/>
        </Form-item>
        <Form-item label="确认新密码" prop="confirm">
          <Input v-model="passwordInfo.confirm" type="password"/>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="primary" @click="confirmEdit('passwordInfo')">确定</Button>
        <Button type="ghost" @click="cancel()">取消</Button>
      </div>
    </Modal>
  </div>
</template>


<script>
  import success from './Success'
  import notice from './Notice'

  export default {
    data () {
      const validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else {
          if (this.passwordInfo.confirm !== '') {
            // 对第二个密码框单独验证
            this.$refs.passwordInfo.validateField('confirm')
          }
          callback()
        }
      }
      const validatePassCheck = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'))
        } else if (value !== this.passwordInfo.newPass) {
          callback(new Error('两次输入密码不一致!'))
        } else {
          callback()
        }
      }
      return {
        successMsg: '',
        errorMsg: '',
        passwordInfo: {
          newPass: '',
          confirm: ''
        },
        showSuccess: false,
        showError: false,
        showEdit: true,
        passwordValidate: {
          newPass: [
            {validator: validatePass, trigger: 'blur'}
          ],
          confirm: [
            {validator: validatePassCheck, trigger: 'blur'}
          ]
        }
      }
    },
    props: ['staffId'],
    components: {
      success,
      notice
    },
    methods: {
      confirmEdit (item) {
        this.$refs[item].validate((valid) => {
          if (valid) {
            this.$http({
              url: 'http://localhost:8081/editPassword',
              method: 'POST',
              params: {
                id: this.staffId,
                password: this.passwordInfo.newPass
              }
            }).then((response) => {
              if (response.body.status) {
                this.successMsg = '成功修改密码'
                this.showSuccess = true
              } else {
                this.errorMsg = '无法修改密码'
                this.showError = true
              }
            }, (response) => {
              this.errorMsg = '无法修改密码，请检查您的网络连接'
              this.showError = true
            })
          } else {
            this.$Message.error('表单验证不通过')
          }
        })
      },
      closeError () {
        this.showError = false
      },
      closeSuccess () {
        this.showSuccess = false
        this.$emit('closeModal')
      },
      cancel () {
        this.$emit('cancel')
      }
    }
  }
</script>
