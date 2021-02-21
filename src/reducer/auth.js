const defaultState = {
    statusLogin: false,
    akses: ""
}

const authReducer = (state = defaultState, action) => {
    switch(action.type) {
        case "login-berhasil":
            return {
                statusLogin: true,
                akses: action.akses
            }
        case "logout-berhasil":
            return {
                statusLogin: false,
                akses: ""
            }
        default:
            return state;
    }
}

export default authReducer;