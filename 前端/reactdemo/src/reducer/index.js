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
            return Object.assign({}, state, {orderTable:[{
                key: '1',
                ID: '0H234',
                Time: '2017-11-11  10:00',
                FoodNum: '12',
                DeskID: '2',
                Money: '1111',
                State:'待付款',
            },{
                key: '2',
                ID: '0H234',
                Time: '2017-11-11  10:00',
                FoodNum: '12',
                DeskID: '1',
                Money: '323',
                State:'已下单',
            },{
                key: '3',
                ID: '0H234',
                Time: '2017-11-11  10:00',
                FoodNum: '12',
                DeskID: '5',
                Money: '323',
                State:'已结账',
            },{
                key: '4',
                ID: '0H234',
                Time: '2017-11-11  10:00',
                FoodNum: '12',
                DeskID: '4',
                Money: '323',
                State:'已结账',
            }]});
            break;
        default:
            return state;
    }
};


export default combineReducers({httpData});
