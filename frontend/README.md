# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can't go back!**

If you aren't satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you're on your own.

You don't have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn't feel obligated to use this feature. However we understand that this tool wouldn't be useful if you couldn't customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: [https://facebook.github.io/create-react-app/docs/code-splitting](https://facebook.github.io/create-react-app/docs/code-splitting)

### Analyzing the Bundle Size

This section has moved here: [https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size](https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size)

### Making a Progressive Web App

This section has moved here: [https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app](https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app)

### Advanced Configuration

This section has moved here: [https://facebook.github.io/create-react-app/docs/advanced-configuration](https://facebook.github.io/create-react-app/docs/advanced-configuration)

### Deployment

This section has moved here: [https://facebook.github.io/create-react-app/docs/deployment](https://facebook.github.io/create-react-app/docs/deployment)

### `npm run build` fails to minify

This section has moved here: [https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify](https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify)

# branch 네이밍 규칙

- frontend
    - front/{function}
- backend
    - back/{function}

# commit message 규칙

- FEAT : 새로운 기능의 추가
- FIX: 버그 수정
- DOCS: 문서 수정
- STYLE: 스타일 관련 기능(코드 포맷팅, 세미콜론 누락, 코드 자체의 변경이 없는 경우)
- REFACTOR: 코드 리펙토링
- TEST: 테스트 코트, 리펙토링 테스트 코드 추가
- CHORE: 빌드 업무 수정, 패키지 매니저 수정(ex .gitignore 수정 같은 경우)

# rebase
- 명령어 : git pull --rebase origin {브랜치 명}
- 작업 브랜치를 원하는 브랜치의 위치로 

- graph 상에 표기
    - 명령어 : git push origin {작업브랜치 명}
    - 그래프상에 작업브랜치를 표기

# workbench
- 13.124.242.194
- https://i6c101.p.ssafy.io:8081/api/chat/room
```{
    "title": "안녕하세요",
    "master_id": 1234,
    "meeting_id": 12345
}
```

# 0207

  // "hat_color": [1,2,3,4,5,6](api - user has meeting) ex(meetingId/userId/Hat - get)

  // speakingtime - idea

# remote 브랜치 가져오기
```bash
$ checkout -t origin/{브랜치 이름}
```
# branch 생성(스위칭 과정 함축)
```bash
git checkout -b front/users frontend
```
# axios test
```javascript
  useEffect(() => {
    axiosTest();
  })
  
  function axiosTest() {
    axios.get(API_BASE_URL + '/users')
      .then(function (response) {
        console.log(response.data)  
      }).catch(function (error) {
        console.log(error)
      });
  }
```

# .then 요청 순서

useEffect 내부 함수 전체 진행 후 
.then 의 값을 요청

# postman용

https://www.notion.so/API-41da035bbe4545528255faee1ddc7a00

- workbench sql
```
use BeyondMeeting;

select * from message;
select * from users;

select * from team;
select * from user_has_team;
select * from meeting;
select * from user_has_meeting;
```

- 회의 참여자 추가 및 모자 색 선택
url : https://i6c101.p.ssafy.io/api/meeting/join

형태
```
{
"meetingId": 2,
"userId": 3,
"hatColor": "RED"
}
```
- 회의 종료
url :https://i6c101.p.ssafy.io/api/meeting/finish
```
{
"meetingId": 1
}
```