import { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom'
import {
  Button,
  Card,
  CardActions,
  CardContent,
  CardHeader,
  Grid,
  Pagination,
} from '@mui/material';
import { getCurrentUser } from '../../util/APIUtils';
import CreateTeam from './CreateTeam';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faStar as solidFaStar } from "@fortawesome/free-solid-svg-icons";
import { faStar as regularFaStar} from "@fortawesome/free-regular-svg-icons";

export function TeamList() {
  const [user, setUser] = useState('');
  const [teams, setTeams] = useState(null);
  const [reLoad, setReload] = useState(true);

  function isLeader(roleType) {
    if (roleType === 'LEADER') {
      return <FontAwesomeIcon icon={solidFaStar}/>
    } else {
      return <FontAwesomeIcon icon={regularFaStar}/>
    }
  }

  useEffect(() => {
    getCurrentUser()
    .then(response => {
      setUser(response);
      setReload(false);
    }).catch(error => {
      console.log(error);
    }); 
  }, [reLoad]);

  useEffect(() => {
    if (user)
      setTeams(user.userHasTeamList.reverse().map((data) => {
        const team = data.team
        const url = `${team.id}`
        return (
          <Grid
            key={team.id}
            item xs={4}
            sx={{p:2}}
          >
            <Card>
              <CardHeader avatar={isLeader(data.roleType)}/>
              <CardContent
                sx={{
                  fontSize: '1.5rem'
              }}>
                {team.teamName}
              </CardContent>
              <CardActions>
                <NavLink
                  to={url}
                  id={team.id}
                  state={{data: data}}
                >
                  <Button>
                   DETAIL 
                  </Button>
                </NavLink>
              </CardActions>
            </Card>
          </Grid>
        )}));
  }, [user]);

  return (
    <Grid container
      justifyContent="center"
    >
      <Grid item
        container
        spacing={2}
        sx={{
          p:5,
          width:"50rem"
        }}
      >
        <Grid item
          container
          xs={12}
          sx={{
            color: '#FFFFFF',
          }}
        >
          <Grid item
            xs={8}
            container
            direction="column"
          >
            <Grid item
            sx={{
              fontSize: '1.5rem'
            }}
            >
              팀 목록 입니다...
            </Grid>
            <Grid item>
              <CreateTeam setReload={setReload}/>
            </Grid>
          </Grid>
          <Grid item
            xs={4}
            container
            direction="column"
            alignItems="flex-end"
          >
            <Grid item>
              <FontAwesomeIcon icon={solidFaStar}/> : 팀장
            </Grid>
            <Grid item>
              <FontAwesomeIcon icon={regularFaStar}/> : 팀원
            </Grid>
          </Grid>
        </Grid>
        <Grid item
          container
          xs={12}
        >
          {teams}
        </Grid>
      </Grid>
    </Grid>
  );
};