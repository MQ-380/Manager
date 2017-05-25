<style scoped>
  .layout {
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    height: 100%;
    width: 100%;
    margin: 0;
    overflow: hidden;
  }

  .layout-content {
    overflow: scroll;
    background: #fff;
    border-radius: 4px;
    padding-bottom: 32676px;
    margin-bottom: -32676px;
  }

  .layout-content-main {
    padding: 20px;
    overflow: scroll;
  }

  .layout-menu-left {
    background: #2e6da4;
    height: 100%;
  }

  .layout-logo-left {
    width: 90%;
    height: 30px;
    background: #ffffff;
    border-radius: 3px;
    margin: 15px auto;
  }

  .layout-ceiling-main a {
    color: #9ba7b5;
  }

  .layout-hide-text .layout-text {
    display: none;
  }

  .layout-full {
    height: 100%;
  }
</style>
<template>
  <div class="layout" :class="{'layout-hide-text': spanLeft < 5}">
    <Row type="flex" class="layout-full">
      <i-col :span="spanLeft" class="layout-menu-left layout-full">
        <Menu active-name="1" theme="dark" width="auto" @on-select="funcChoose" class="layout-menu-left layout-full">
          <div class="layout-logo-left">
          </div>
          <Menu-item name="sign">
            <Icon type="ios-time-outline"></Icon>
            <span class="layout-text">签到/签离</span>
          </Menu-item>
          <Menu-item name="apply">
            <Icon type="ios-people-outline" ></Icon>
            <span class="layout-text">外出申请</span>
          </Menu-item>
          <Menu-item name="salary">
            <Icon type="ios-information-outline" ></Icon>
            <span class="layout-eye-outline">工资查看</span>
          </Menu-item>
          <Menu-item name="staff">
            <Icon type="ios-gear-outline" ></Icon>
            <span class="layout-text">员工信息</span>
          </Menu-item>
          <Menu-item name="staffSalary">
            <Icon type="ios-gear-outline" ></Icon>
            <span class="layout-text">员工工资</span>
          </Menu-item>
          <Menu-item name="confirm">
            <Icon type="ios-gear-outline" ></Icon>
            <span class="layout-text">外出申请审批</span>
          </Menu-item>
          <Menu-item name="setting">
            <Icon type="ios-gear-outline" ></Icon>
            <span class="layout-text">个人设置/登出</span>
          </Menu-item>
        </Menu>
      </i-col>
      <i-col :span="spanRight">
        <div class="layout-content">
          <div class="layout-content-main">
            <router-view></router-view>
          </div>
        </div>
      </i-col>
    </Row>

    <Modal v-model="isLoginCorrect" :closable="false" :mask-closable="false">
      <p slot="header" style="color:#843534;text-align:left">
        <span>请先登录</span>
      </p>
      <div style="text-align:center">
        <span>请先登录或请检查您的登录账号种类</span>
      </div>
      <div slot="footer">
        <Button type="primary" @click="toLogin">登录页面</Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        notice: false,
        spanLeft: 5,
        spanRight: 19,
        msg: '',
        showError: false
      }
    },
    computed: {
      isLoginCorrect: function () {
        this.$store.commit('GETLOGIN')
        return !(this.$store.state.LoginState.isLogin && this.$store.state.LoginState.loginType !== '1' && this.$store.state.LoginState.loginType !== '0')
      }
    },
    methods: {
      toLogin () {
        this.$router.push('/')
      },
      closeModal () {
        this.showError = false
      },
      funcChoose (name) {
        if (name === 'sign') {
          this.$router.push('/mSign')
        } else if (name === 'apply') {
          this.$router.push('/mApply')
        } else if (name === 'salary') {
          this.$router.push('/mSalary')
        } else if (name === 'setting') {
          this.$router.push('/mSetting')
        } else if (name === 'staff') {
          this.$router.push('/mStaff')
        } else if (name === 'staffSalary') {
          this.$router.push('/mStaffSalary')
        } else if (name === 'confirm') {
          this.$router.push('/mConfirmApply')
        }
      }
    }
  }
</script>
