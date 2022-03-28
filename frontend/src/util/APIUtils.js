import { API_BASE_URL, ACCESS_TOKEN } from '../constants';
import axios from 'axios';

function request (options) {
    const headers = new Headers({
        'Content-Type': 'application/json',
    })
    
    if(localStorage.getItem(ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
    }

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return fetch(options.url, options)
    .then(response => 
        response.json().then(json => {
            if(!response.ok) {
                return Promise.reject(json);
            }
            return json;
        })
    );
};

//-------------------------------USER READ-----------------------------

export function getCurrentUser() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/user/me",
        method: 'GET'
    });
    
}

export function getUsers() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/users",
        method: 'GET'
    });
}

// export function getOneUser(id) {
//     if(!localStorage.getItem(ACCESS_TOKEN)) {
//         return Promise.reject("No access token set.");
//     }

//     return request({
//         url: API_BASE_URL + "/user/"+id,
//         method: 'GET'
//     });
// }

//-------------------------------USER CREATE-----------------------------
//혹시 그냥 로그인이나 회원가입 할 때 
// export function login(loginRequest) {
//     return request({
//         url: API_BASE_URL + "/auth/login",
//         method: 'POST',
//         body: JSON.stringify(loginRequest)
//     });
// }

// export function signup(signupRequest) {
//     return request({
//         url: API_BASE_URL + "/auth/signup",
//         method: 'POST',
//         body: JSON.stringify(signupRequest)
//     });
// }

//-------------------------------USER DELETE-----------------------------

// export function deleteUser(id) {
//     return request({
//         url: API_BASE_URL + "/user/"+id,
//         method: 'DELETE',
//     });
// }

//-------------------------------TEAM CREATE-----------------------------
export function createTeam(createTeamRequest) {
    return request({
        url: API_BASE_URL + "/team",
        method: 'POST',
        body: JSON.stringify(createTeamRequest)
    });
}

export function inviteTeamMember(inviteTeamMemberRequest) {
    return request({
        url: API_BASE_URL + "/team/member",
        method: 'POST',
        body: JSON.stringify(inviteTeamMemberRequest)
    });
}

//-------------------------------TEAM READ-----------------------------
export function getTeams() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/teams",
        method: 'GET'
    });
}

export function getOneTeam(teamId) {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/team/member/" + teamId,
        method: 'GET'
    });
}

//-------------------------------TEAM UPDATE-----------------------------
export function updateTeamName(teamId,UpdateTeamNameReq) {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }
    return request({
        url: API_BASE_URL + "/team/" + teamId,
        method: 'PUT',
        body: JSON.stringify(UpdateTeamNameReq)
    });
}

export function updateLeader(teamId,updateLeaderReq) {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }
    return request({
        url: API_BASE_URL + "/team/leader/" + teamId,
        method: 'PUT',
        body: JSON.stringify(updateLeaderReq)
    });
}

//-------------------------------TEAM DELETE-----------------------------
export function deleteTeam(teamId) {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }
    return request({
        url: API_BASE_URL + "/team/" + teamId,
        method: 'DELETE'
    });
}

export function deleteTeamMember(teamId,userId) {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }
    return request({
        url: API_BASE_URL + "/team/member/" + teamId + "/" + userId,
        method: 'DELETE'
    });
}


// ============================== 메시지 조회 ==============================


/** 메시지 전체 리스트 조회 */
export function getMessages() {
    return axios
        ({
            method: "GET",
            url: API_BASE_URL + "/messages",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
            }           
        })
        
}

/** 특정 회의 아이디를 갖는 메시지 리스트 조회 */
export function getMeesagesByMeetingId(meetingId) {
    return axios
        ({
            method: "GET",
            url: API_BASE_URL + "/message/" + meetingId,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
            }           
        })
}


// ============================== 회의 조회 ==============================


/** 회의 전체 리스트 조회 */
export function getMeetings() {
    return axios
        ({
            method: "GET",
            url: API_BASE_URL + "/meetings",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
            }           
        })
}

/** 특정 회의 아이디를 갖는 회의 단건 조회 */
export function getMeetingByMeetingId(meetingId) {
    return axios
        ({
            method: "GET",
            url: API_BASE_URL + "/meeting/" + meetingId,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
            }           
        })
}

/** 특정 팀 아이디를 갖는 회의 리스트 조회 */
export function getMeetingsByTeamId(teamId) {
    return axios
        ({
            method: "GET",
            url: API_BASE_URL + "/meeting/team/" + teamId,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
            }           
        })
}

/** 회의 참여자 전체 리스트 조회 */
export function getAttenders() {
    return axios
        ({
            method: "GET",
            url: API_BASE_URL + "/attenders",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
            }           
        })
}

/** 특정 회의 아이디를 갖는 회의 참여자 리스트 조회 */
export function getAttendersByMeetingId(meetingId) {
    return axios
        ({
            method: "GET",
            url: API_BASE_URL + "/attender/meeting/" + meetingId,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
            }           
        })
}

/** 내 유저 아이디로 미팅 참여 정보 조회 */
export function getAttendersByUserId(userId) {
    return axios
        ({
            method: "GET",
            url: API_BASE_URL + "/attender/user/" + userId,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
            }           
        })
}


/** { 모자색깔, 해당 모자를 쓰고 참여한 회의 전체 시간 } 형태로 return */
export function getAttederWithHat(userId) {
    return axios
    ({
        method: "GET",
        url: API_BASE_URL + "/attender/hat/" + userId,
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
        }           
    })
}

// ============================== 회의 생성, 참여, 종료 ==============================


/** 미팅 생성 */
export function createMeeting(requestCreateMeeting) {
    return axios({
        method: "POST",
        url: API_BASE_URL + "/meeting/create",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
        }, 
        data: {
            topic: requestCreateMeeting.topic,
            meetingType: requestCreateMeeting.meetingType,
            teamId: requestCreateMeeting.teamId
        }
    })
}

/** 미팅 참여 */
export function joinMeeting(requestJoinMeeting) {
    return axios({
        method: "POST",
        url: API_BASE_URL + "/meeting/join",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
        }, 
        data: {
            meetingId: requestJoinMeeting.meetingId,
            userId: requestJoinMeeting.userId,
            hatColor: requestJoinMeeting.hatColor
        }
    })
}

/** 미팅 종료 */
export function finishMeeting(requestFinishMeeting) {
    return axios({
        method: "POST",
        url: API_BASE_URL + "/meeting/finish",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
        }, 
        data: {
            meetingId: requestFinishMeeting.meetingId
        }
    })
}

//hatchart/longtime

// ============================== 차트 ==============================

/** YYYYMM 기준으로 미팅참여횟수 조회 */
export function getAttenderDateByUserId(userId) {
    return axios
        ({
            method: "GET",
            url: API_BASE_URL + "/attender/date/" + userId,
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
            }           
        })
}

export function getHatLongTime() {
    return axios
        ({
            method: "GET",
            url: API_BASE_URL + "/hatchart/longtime",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
            }           
        })
}

///meetings/count
export function getMyMeetingCount() {
    return axios
        ({
            method: "GET",
            url: API_BASE_URL + "/meetings/count",
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem(ACCESS_TOKEN)
            }           
        })
}