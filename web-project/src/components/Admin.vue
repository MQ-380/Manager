<style scoped>
  .layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
    height: 100%;
    width: 100%;
    margin: 0;
  }
  .layout-content{
    min-height: 200px;
    margin: 15px;
    overflow: hidden;
    background: #fff;
    border-radius: 4px;
    height: 100%;
  }
  .layout-content-main{
    padding: 10px;
  }
  .layout-menu-left{
    background: #464c5b;
  }
  .layout-header{
    height: 60px;
    background: #fff;
    box-shadow: 0 1px 1px rgba(0,0,0,.1);
  }
  .layout-logo-left{
    width: 90%;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    margin: 15px auto;
  }
  .layout-ceiling-main a{
    color: #9ba7b5;
  }
  .layout-hide-text .layout-text{
    display: none;
  }
  .layout-header-right{
    float: right;
  }
  .layout-header-left{
    float: left;
  }
  .layout-full{
    height: 100%;
  }
</style>
<template>
  <div class="layout" :class="{'layout-hide-text': spanLeft < 5}">
    <Row type="flex" class="layout-full">
      <i-col :span="spanLeft" class="layout-menu-left layout-full">
        <Menu active-name="1" theme="dark" width="auto" @on-select="logout">
          <div class="layout-logo-left"></div>
          <Menu-item name="1">
            <Icon type="ios-people" :size="iconSize"></Icon>
            <span class="layout-text">员工管理</span>
          </Menu-item>
          <Menu-item name="LogOut">
            <Icon type="ios-information" :size="iconSize"></Icon>
            <span class="layout-text">登出此账户</span>
          </Menu-item>
        </Menu>
      </i-col>
      <i-col :span="spanRight">
        <div class="layout-header">
          <i-button type="text" @click="toggleClick" class="layout-header-left">
            <Icon type="navicon" size="32"></Icon>
          </i-button>
          <div class="layout-header-right">
            <span>当前登录账号：{{adminId}}</span>
          </div>
        </div>
        <div class="layout-content">
          <div class="layout-content-main">

          </div>
        </div>
      </i-col>
    </Row>

    <Modal v-model="isLoginCorrect">
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

    <Modal v-model="notice" @on-ok="toLogin">
      <p slot="header" style="color:#843534;text-align:left">
        <span>注意</span>
      </p>
      <div style="text-align:center">
        <span>请确定是否要登出账户？</span>
      </div>
    </Modal>

  </div>
</template>
<script>
  export default {
    name: 'admin',
    data () {
      return {
        notice: false,
        spanLeft: 5,
        spanRight: 19
      }
    },
    computed: {
      iconSize () {
        return this.spanLeft === 5 ? 14 : 24
      },
      isLoginCorrect: function () {
        this.$store.commit('GETLOGIN')
        return !(this.$store.state.isLogin && this.$store.state.loginType === '1')
      },
      adminId: function () {
        this.$store.commit('GETLOGIN')
        return this.$store.state.loginId
      }
    },
    methods: {
      toggleClick () {
        if (this.spanLeft === 5) {
          this.spanLeft = 2
          this.spanRight = 22
        } else {
          this.spanLeft = 5
          this.spanRight = 19
        }
      },
      toLogin () {
        this.$router.push('/')
      },
      logout (name) {
        if (name === 'LogOut') {
          this.notice = true
        }
      }
    }
  }
</script>

