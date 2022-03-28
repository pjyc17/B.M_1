import { useLocation, NavLink } from 'react-router-dom'
import { 
    Grid,
    Card,
    CardContent,
    Button,
    CardHeader,
    CardActions,
} from '@mui/material';
import { CreateMeeting } from './CreateMeeting'
import { useEffect, useState } from 'react';
import { getMeetingsByTeamId, getUsers } from '../../util/APIUtils';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHatCowboy, faFaceGrin } from "@fortawesome/free-solid-svg-icons";

export function MeetingList() {
  const { state } = useLocation();
  const roleType = state.data.roleType;
  const teamId = state.data.team.id;
  const teamName = state.data.team.teamName;
  const user = state.data.user;
  const [users, setUsers] = useState([]);
  const [meetingList, setMeetingList] = useState(<Grid>회의를 생성하세요</Grid>);
  const [reLoad, setReload] = useState(true);

  useEffect(() => {
    getUsers()
    .then(response => {
      setUsers(response.map(user => ({id: user.id, email: user.email})));
      setReload(false);
    }).catch(error => {
      console.log(error);
    });
  }, [reLoad]);

  function meetingType(meetingType) {
    if (meetingType === 'SIXHAT') {
      return <FontAwesomeIcon icon={faHatCowboy} />
    } else {
      return <FontAwesomeIcon icon={faFaceGrin} />
    }}

  function toMeeting(url, meeting, user) {
    if (meeting.endTime) {
      return (
        <NavLink
          to={url.concat('/result')} 
          state={{meeting: meeting}}
        >
          DETAIL
        </NavLink>
      )
    } else {
      return (
        <NavLink
          to={url.concat('/room')}
          state={{meeting: meeting, user: user, roleType: roleType}}
        >
          NOW ON...
        </NavLink>
      )
    }
  }

  useEffect(() => {
    getMeetingsByTeamId(teamId)
    .then((response => {
      setMeetingList(response.data.reverse().map(meeting => {
        const url = `${meeting.id}`
        return (
          <Grid
            key={meeting.id}
            item xs={4}
            sx={{
              p:1
            }}
          >
            <Card>
              <CardHeader
                avatar={meetingType(meeting.meetingType)}
              />
              <CardContent>
                {meeting.topic}
              </CardContent>
              <CardActions>
                {toMeeting(url, meeting, user)}
              </CardActions>
            </Card>
          </Grid>
        )}))
      })).catch(((error) => {
        console.log(error)
      }));
    }, [teamId, user]);
    
    return (
      <Grid container
        justifyContent="center"
      >
        <Grid item
          container
          spacing={2}
          sx={{
            p: 5,
            color: '#FFFFFF',
            width:"50rem"
          }}
        >
          <Grid item xs={8}
            container
            direction="column"
          >
            <Grid item
              sx={{
                fontSize: '1.5rem',
              }}
            >
              {teamName}
            </Grid>
            <Grid item
              container
              alignItems="center"
            >
              { roleType === 'LEADER' && (
                <Grid item
                >
                  <NavLink
                    to='update'
                    state={{
                      teamId:teamId,
                      teamLeaderId:user.id,
                      users:users,
                      teamName:teamName
                    }}
                  >
                    <Button variant="contained">
                    팀 관리
                    </Button>
                  </NavLink>
                </Grid>
              )}
              { roleType === 'LEADER' && <CreateMeeting teamId={teamId}/> }
            </Grid>
          </Grid>
          <Grid item xs={4}
            container
            direction="column"
            justifyContent="center"
            alignItems="flex-end"  
          >
            <Grid item>
              <FontAwesomeIcon icon={faHatCowboy}/> : 육색 모자
            </Grid>
            <Grid item>
              <FontAwesomeIcon icon={faFaceGrin}/> : 일반
            </Grid>
          </Grid>
          <Grid item xs={12} container>
            {meetingList}
          </Grid>
        </Grid>
      </Grid>
  );
};
