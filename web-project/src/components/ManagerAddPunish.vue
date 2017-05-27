<template>
  <div id="NewPunish">
    <success v-if="showSuccess" :msg="successMsg" @closeModal="closeSuccess"></success>
    <notice v-if="showError" :infoname="errorMsg" @closeModal="closeError"></notice>
    <Modal v-model="showApply" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>新建奖惩</span>
      </p>
      <Form ref="punishInfo" :model="punishInfo" :label-width="80" :rules="punishValidate">
        <Form-item label="姓名" prop="name">
          <Select v-model="punishInfo.name" style="width:200px; float: left;">
            <Option v-for="item in detail" :value="item.name" :key="item">{{item.name}}</Option>
          </Select>
        </Form-item>
        <Form-item label="工号" prop="staid">
          <Input v-model="punishInfo.staffId" readonly/>
        </Form-item>
        <Form-item label="种类" prop="type">
          <Radio-group v-model="punishInfo.type" style="float: left">
            <Radio label="1">奖</Radio>
            <Radio label="0">惩</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="金额" prop="amount">
          <Input v-model="punishInfo.amount" />
        </Form-item>
        <Form-item label="添加时间" prop="time">
          <Input v-model="punishInfo.Addtime" readonly/>
        </Form-item>
        <Form-item label="事由" prop="detail">
          <Input type="textarea" v-model="punishInfo.detail"/>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="primary" @click="confirm('punishInfo')">确定</Button>
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
      const NumberValidate = (rule, value, callback) => {
        if (isNaN(Number(value))) {
          callback(new Error('请填写数字'))
        } else {
          callback()
        }
      }
      return {
        punishInfo: {
          staffId: '',
          name: '',
          type: '',
          amount: '',
          Addtime: new Date(this.time).toLocaleDateString(),
          detail: ''
        },
        punishValidate: {
          type: [
            {required: true, message: '请选择申请种类', trigger: 'blur'}
          ],
          amount: [
            {required: true, message: '请填写金额', trigger: 'blur'},
            {validator: NumberValidate, trigger: 'blur'}
          ],
          detail: [
            {max: 1000, message: '不得超过1000字', trigger: 'blur'}
          ]
        },
        showApply: true,
        showSuccess: false,
        showError: false,
        successMsg: '',
        errorMsg: ''
      }
    },
    components: {
      notice,
      success
    },
    props: ['detail', 'time', 'id'],
    watch: {
      'punishInfo.name': 'setId'
    },
    methods: {
      setId () {
        let name = this.punishInfo.name
        let id
        this.detail.forEach(function (item) {
          if (item.name === name) {
            id = item.staid
          }
        })
        this.punishInfo.staffId = id
      },
      confirm (item) {
        this.$refs[item].validate((valid) => {
          this.$store.commit('GETLOGIN')
          if (valid) {
            this.$http({
              url: 'http://localhost:8081/addBounsPunish',
              method: 'POST',
              params: {
                'repun.staid': this.punishInfo.staffId,
                'repun.type': this.punishInfo.type,
                'repun.amount': this.punishInfo.amount,
                'repun.date': this.getFormatDate(new Date(this.punishInfo.Addtime)),
                'repun.description': this.punishInfo.detail === undefined ? '' : this.punishInfo.detail,
                'repun.operator': this.$store.state.LoginState.loginId
              }
            }).then((response) => {
              if (response.body.status) {
                this.successMsg = '添加奖惩成功'
                this.showSuccess = true
              } else {
                this.errorMsg = '暂时无法添加奖惩，请稍后重试'
                this.showError = true
              }
            }, (response) => {
              this.errorMsg = '无法添加奖惩，请检查您的网络连接'
              this.showError = true
            })
          }
        })
      },
      getFormatDate (date) {
        return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
      },
      closeSuccess () {
        this.showSuccess = false
        this.$emit('closeModal')
      },
      closeError () {
        this.showError = false
      },
      cancel () {
        this.$emit('closeModal')
      }
    }
  }
</script>
