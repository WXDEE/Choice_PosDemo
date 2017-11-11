/**
 * Created by Knove on 2017/11/9.
 */
import React from 'react';
import InfoTab from './InfoComponents/InfoTab';

class FoodInfo extends React.Component{
    render(){
        return(
            <div >
                <InfoTab infoNum="2" />
              后台运维页面-菜品信息
            </div>
        )
    }
}
export default FoodInfo;
