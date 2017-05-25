import Vue from 'vue'
import Router from 'vue-router'
import Login from '../components/Login.vue'
import Admin from '../components/Admin.vue'
import DepartManage from '../components/DepartmentManage.vue'
import StaffManage from '../components/StaffManage.vue'
import Employee from '../components/Employee.vue'
import Setting from '../components/EmployeeSetting.vue'
import Sign from '../components/EmployeeSignIn.vue'
import Salary from '../components/EmployeeSalary.vue'
import Apply from '../components/EmployeeApply.vue'
import Manager from '../components/Manager.vue'
import Staff from '../components/ManagerStaff.vue'
import StaffSalary from '../components/ManagerStaffSalary.vue'
import ConfirmApply from '../components/ManagerConfirmApply.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/Admin',
      name: 'Admin',
      children: [
        {
          path: '/Depart',
          component: DepartManage
        },
        {
          path: '/Staff',
          component: StaffManage
        }
      ],
      component: Admin
    },
    {
      path: '/Employee',
      name: 'employee',
      component: Employee,
      children: [
        {
          path: '/Setting',
          component: Setting
        },
        {
          path: '/Sign',
          component: Sign
        },
        {
          path: '/Apply',
          component: Apply
        },
        {
          path: '/Salary',
          component: Salary
        }
      ]
    },
    {
      path: '/Manager',
      name: 'manager',
      component: Manager,
      children: [
        {
          path: '/mSetting',
          component: Setting
        },
        {
          path: '/mSign',
          component: Sign
        },
        {
          path: '/mApply',
          component: Apply
        },
        {
          path: '/mSalary',
          component: Salary
        },
        {
          path: '/mStaff',
          component: Staff
        },
        {
          path: '/mStaffSalary',
          component: StaffSalary
        },
        {
          path: '/mConfirmApply',
          component: ConfirmApply
        }
      ]
    }
  ]
})
