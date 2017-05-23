<template>
  <div id="NewApplyForm">
    <success v-if="showSuccess" :msg="successMsg" @closeModal="closeSuccess"></success>
    <notice v-if="showError" :infoname="errorMsg" @cancel="closeError"></notice>
    <Modal v-model="showApply" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>新建外出申请</span>
      </p>
      <Form ref="applyInfo" :model="applyInfo" :label-width="80" :rules="applyValidate">
        <Form-item label='员工编号' prop="staffId">
          <Input v-model="applyInfo.staffId" readonly/>
        </Form-item>
        <Form-item label="姓名" prop="name">
          <Input v-model="applyInfo.name" readonly/>
        </Form-item>
        <Form-item label="部门" prop="depart">
          <Input v-model="applyInfo.depart" readonly/>
        </Form-item>
        <Form-item label="申请种类" prop="type">
          <Radio-group v-model="applyInfo.type" style="float: left">
            <Radio label="1">请假</Radio>
            <Radio label="2">公事外出</Radio>
          </Radio-group>
        </Form-item>
        <Form-item label="起始时间" prop="time">
          <Date-picker type="daterange" v-model="applyInfo.time" placeholder="选择起始日期"
                       style="width: 200px;float: left"></Date-picker>
        </Form-item>
        <Form-item label="事由" prop="detail">
          <Input type="textarea" v-model="applyInfo.detail"/>
        </Form-item>
      </Form>
      <div slot="footer">
        <Button type="primary" @click="confirm('applyInfo')">确定</Button>
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
      const TimeValidate = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请选择起始时间'))
        } else {
          callback()
        }
      }

      return {
        applyInfo: {
          staffId: '',
          name: '',
          depart: '',
          deid: '',
          type: '',
          time: [],
          detail: ''
        },
        applyValidate: {
          type: [
            {required: true, message: '请选择申请种类', trigger: 'blur'}
          ],
          time: [
            {validator: TimeValidate, trigger: 'blur'}
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
    created () {
      this.setInfo()
    },
    watch: {
      '$route': 'setInfo'
    },
    methods: {
      setInfo () {
        let id = this.$store.state.LoginState.loginId
        let infoId
        this.$http({
          url: 'http://localhost:8081/consultPersonalInformation',
          params: {
            Id: id
          }
        }).then((response) => {
          let info = response.body
          if (info.hasOwnProperty('staff')) {
            this.$store.commit('SETSTAFFINFO', info.staff)
            this.applyInfo.staffId = info.staff.staid
            this.applyInfo.name = info.staff.name
            infoId = info.staff.deid
          } else {
            this.errorMsg = '无法获取员工信息'
            this.showError = true
          }
        }, (response) => {
          this.errorMsg = '网络错误，请检查网络连接'
          this.showError = true
        })
        this.$store.commit('GETSTAFFINFO')
        let name
        this.$http.post('http://localhost:8081/consultDepartment.action')
            .then((response) => {
              this.$store.commit('SETDEPARTDATA', response.body.department)
              response.body.department.forEach(function (item) {
                if (item.deid === infoId) {
                  name = item.name
                }
              })
              this.applyInfo.depart = name
            },
            (response) => {
              this.errorMsg = '网络错误，请检查您的网络连接'
              this.showError = true
            })
      },
      confirm (item) {
        this.$refs[item].validate((valid) => {
          if (valid) {
            this.$http({
              url: 'http://localhost:8081/addNewLeavingApply',
              method: 'POST',
              params: {
                'Applyleave.staid': this.applyInfo.staffId,
                'Applyleave.type': this.applyInfo.type,
                'Applyleave.stime': this.applyInfo.time[0].toLocaleDateString(),
                'Applyleave.etime': this.applyInfo.time[1].toLocaleDateString(),
                'Applyleave.description': this.applyInfo.detail === undefined ? '' : this.applyInfo.detail
              }
            }).then((response) => {
              if (response.body.status) {
                this.successMsg = '成功申请外出，请及时关注申请的最新状态'
                this.showSuccess = true
              } else {
                this.errorMsg = '暂时无法申请外出，请稍后重试'
                this.showError = true
              }
            }, (response) => {
              this.errorMsg = '无法申请外出，请检查您的网络连接'
              this.showError = true
            })
          }
        })
      },
      closeSuccess () {
        this.showSuccess = false
        this.$emit('closeSuccess')
      },
      closeError () {
        this.showError = false
      },
      cancel () {
        this.$emit('cancel')
      }
    }
  }
</script>
