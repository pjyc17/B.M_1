// 로컬 테스트
export const API_BASE_URL = 'https://localhost:8082/api';
export const OAUTH2_REDIRECT_URI = 'http://localhost:3000/oauth2/redirect'

// 배포용
// export const API_BASE_URL = 'https://i6c101.p.ssafy.io/api';
// export const OAUTH2_REDIRECT_URI = 'https://i6c101.p.ssafy.io/oauth2/redirect'


// 로그인 토큰
export const ACCESS_TOKEN = 'accessToken';

// 구글 로그인 URL
export const GOOGLE_AUTH_URL = API_BASE_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;