/**
 * Created by mouizumi on 2017/5/12.
 */

function makeAction (type) {
  return ({ dispatch }, ...args) => dispatch(type, ...args)
}

export const Init = makeAction('INIT_STATE')
export const LoginInfo = makeAction('LOGIN')
export const Logout = makeAction('LOGOUT')
