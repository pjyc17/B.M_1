import * as React from 'react';
import Paper from '@mui/material/Paper';
import {
  ArgumentAxis,
  ValueAxis,
  Chart,
  LineSeries,
} from '@devexpress/dx-react-chart-material-ui';
import { Animation } from '@devexpress/dx-react-chart';
import { getCurrentUser, getAttenderDateByUserId } from '../../util/APIUtils';
import { useEffect, useState } from 'react';
import LoadingIndicator from '../../common/LoadingIndicator';


export default function LineChart() {

  const [loading, setLoading] = useState(true);
  const [currentUserId, setCurrentUserId] = useState(0);


  const [data, setData] = useState([ ]);
  const aaa = {
    argument: "13월", value: 30
  }

        
  useEffect(() => {
    getCurrentUser()
         .then(response => {
           setCurrentUserId(response.id)
          //  console.log(response.id)     

          setLoading(false)
        }).catch(error => {
          console.log(error)
          setLoading(false)
        });
      }, []);

  useEffect(() => {
    if(currentUserId)
    getAttenderDateByUserId(currentUserId)
         .then(response => {
          //  console.log(response.data)     
          setData(response.data)
          
          // setData([...data,aaa])

          setLoading(false)
        }).catch(error => {
          console.log(error)
          setLoading(false)
        });
      }, [currentUserId]);


    return (
  <Paper>
    <Chart
      data={data}
    >
      <ArgumentAxis />
      <ValueAxis />
       {loading ? <LoadingIndicator /> : (<LineSeries valueField="value" argumentField="argument" />)}
      <Animation />
    </Chart>
  </Paper>
  )
}
