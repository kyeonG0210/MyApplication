/* <두 개 이상의 화면을 가지는 app>
[ 인텐트: 컴포넌트를 실행하려고 시스템에 전달하는 메시지 ]
- 안드로이드의컴포넌트 클래스라면 개발자가 코드에서 직접 생성해서 실행할 수 없음.
- 시스템에서 인텐트의 정보를 분석해서 그에 맞는 컴포넌트를 실행해줌.
- 외부 앱의 컴포넌트와 연동할 때도 마찬가지

- startActivity() 함수가 인텐트를 시스템에 전달
- intent 생성자의 매개변수는 클래스 타입 레퍼런스 정보

- 인텐트에 컴포넌트 실행을 요청할 때 데이터를 함께 전달하려면 '엑스트라 데이터(인텐트에 담는 부가 정보)'
- 인텐트에 엑스트라 데이터를 추가하는 함수 = putExtra()
- 인텐트 객체의 'get(가지고 오는 데이터 타입)Extra()' 함수로 데이터를 가져오면 됨. ex)getStringExtra. getIntExtra

[인텐트로 액티비티를 시작하는 방법]
- public void startActivity(Intent intent)
- public void startActivityForResult(Intent intent, int requestCode)
- ActivityResultLauncher <- 요즘 거의 사용됨. 실습때 사용한 방법

[결과 되돌리기]
- 자동으로 화면을 되돌릴 때는 finish()함수를 이용
- 예시)
intent.putExtra("result", scores) //돌아가기 버튼을 눌렀을 때 값을 돌려줌
setResult(RESULT_OK, intent) //mainactivity로 넘어가고 스스로 종료
finish()

[생명주기]
종료하고 시작하면 onCreate()부터
* */