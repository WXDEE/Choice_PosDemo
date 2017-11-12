/**
 * Created by Knove on 2017/11/9.
 */
import React from 'react';
import InfoTab from './InfoComponents/InfoTab';
import {Table,Icon} from 'antd';

class FoodInfo extends React.Component{
    constructor(props){
        super(props);
        this.state={
        }
    }
    render(){
        return(
            <div >
                <InfoTab infoNum="2" />

            </div>
        )
    }
}
export default FoodInfo;
