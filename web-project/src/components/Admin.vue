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
    <LogoutNotice v-if="showLogOut"></LogoutNotice>
    <Row type="flex" class="layout-full">
      <i-col :span="spanLeft" class="layout-menu-left layout-full">
        <Menu active-name="1" theme="dark" width="auto" @on-select="logout" class="layout-menu-left layout-full">
          <div class="layout-logo-left">
          </div>
          <Menu-item name="depart">
            <Icon type="ios-people" :size="iconSize"></Icon>
            <span class="layout-text">部门管理</span>
          </Menu-item>
          <Menu-item name="staff">
            <Icon type="ios-person" :size="iconSize"></Icon>
            <span class="layout-text">员工管理</span>
          </Menu-item>
          <Menu-item name="LogOut">
            <Icon type="ios-information" :size="iconSize"></Icon>
            <span class="layout-text">登出账户</span>
          </Menu-item>
        </Menu>
      </i-col>
      <i-col :span="spanRight">
        <div class="layout-content">
          <div class="layout-content-main" >
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
  import Depart from './DepartmentManage'
  import Notice from './Notice'
  import Staff from './StaffManage'
  import LogoutNotice from './LogoutNotice'
  export default {
    name: 'admin',
    data () {
      return {
        notice: false,
        spanLeft: 5,
        spanRight: 19,
        showLogOut: false
      }
    },
    computed: {
      iconSize () {
        return this.spanLeft === 5 ? 14 : 24
      },
      isLoginCorrect: function () {
        this.$store.commit('GETLOGIN')
        return !(this.$store.state.LoginState.isLogin && this.$store.state.LoginState.loginType === '0')
      }
    },
    components: {
      Staff,
      Notice,
      Depart,
      LogoutNotice
    },
    methods: {
      toLogin () {
        this.$router.push('/')
      },
      logout (name) {
        if (name === 'LogOut') {
          this.showLogOut = true
        } else if (name === 'depart') {
          this.$router.push('/Depart')
        } else if (name === 'staff') {
          this.$router.push('/Staff')
        }
      }
    }
  }
</script>

