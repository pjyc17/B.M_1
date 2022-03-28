import TimeLog from './TimeLog';
import Info from './Info';
import './Profile.css';
import { useEffect, useState } from 'react';
import { getCurrentUser } from '../../util/APIUtils';
import LoadingIndicator from '../../common/LoadingIndicator';
import { Grid } from '@mui/material';


export function Profile() {
  const [user, setUser] = useState('');
  const [userHasMeetingList, setUserHasMeetingList] = useState();
  const [loading, setLoading] = useState(true);
  const [hatInfo, setHatInfo] = useState(false);

  // useEffect(() => {
  //   if (userId)
  //     getOneUser(userId)
  //     .then(response => {
  //       setUser(response)
  //       const num = (response.userHasMeetingList).length
  //       let sum = 0;
  //       for (let i = 0; i < num; i++) {
  //         let end = new Date(response.userHasMeetingList[i].meeting.endTime)
  //         let start = new Date(response.userHasMeetingList[i].meeting.startTime)
  //         let diff = (end.getTime() - start.getTime())
  //         sum = sum + diff;
  //       }
  //       console.log(sum)
  //     }).catch(error => {
  //       console.log(error)
  //     });
  // }, [userId]);

  useEffect(() => {
    getCurrentUser()
    .then(response => {
      setUser(response)
      // setLoading(false)
      // console.log(userHasMeetingList)
      // console.log(response.userHasMeetingList)
    }).catch(error => {
      console.log(error)
      setLoading(false)
      console.log(loading)
    });
  }, []);

  useEffect(() => {
    if(user)
    setUserHasMeetingList(user.userHasMeetingList)
    // console.log('1')
  },[user])

  useEffect(() => {
    if(userHasMeetingList)
    setHatInfo(true)
    // console.log('2', userHasMeetingList)
  },[userHasMeetingList]);

  // useEffect(() => {
  //   if(hatInfo)
  //   console.log('3', hatInfo)
  // },[hatInfo])
  



  // function Timelog () {
  //   if(userHasMeetingList) {
  //     return (
  //       <div>
  //         <TimeLog user={user} userHasMeetingList={userHasMeetingList}></TimeLog>
  //       </div>
  //     )
  //     } else {
  //     return (
  //     <Grid item container xs={10} rowSpacing={5}>
  //       <h1>프로필이 허전하네요 회의에 참여해봅시다.</h1>
  //     </Grid>
  //     )
  //   }
  // }
  
  return (
  <Grid container columnSpacing={5}>
    <Info className="kkwak" user={user}></Info>
    <TimeLog user={user} userHasMeetingList={userHasMeetingList}></TimeLog>

  </Grid>
  )
};