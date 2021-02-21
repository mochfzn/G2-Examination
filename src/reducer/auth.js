const defaultState = {
    statusLogin: false,
    akses: "",
    kasir: { }
}

const authReducer = (state = defaultState, action) => {
    switch(action.type) {
        case "login-berhasil":
            return {
                statusLogin: true,
                akses: action.akses,
                kasir: action.kasir
            }
        case "logout-berhasil":
            return {
                statusLogin: false,
                akses: "",
                kasir: { }
            }
        default:
            return state;
    }
}

export default authReducer;