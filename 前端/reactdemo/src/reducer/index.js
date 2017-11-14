/**
 * Created by Knove on 2017/11/10.
 */
import { combineReducers } from 'redux';
import * as type from '../action/type';

const initialList = {
    theNumber: 0,
    theString:"initial string!",
}
const httpData = (state = initialList, action) => {
    switch (action.type) {
        case type.RECEIVE_DATA:
            return Object.assign({}, state, { theNumber: state.theNumber + 1 });
            break;
        case type.REQUEST_DATA:
            return Object.assign({}, state, { theNumber: state.theNumber - 1 });
            break;
        case type.REQUEST_ORDER:
            return Object.assign({}, state, action.data);
            break;
        case type.REQUEST_FOOD:
            return Object.assign({},state, action.data);
            break;
        case type.POINT_DESK:
            return Object.assign({}, state, {deskNumber:action.deskNumber});
            break;
        default:
            return state;
    }
};


export default combineReducers({httpData});
