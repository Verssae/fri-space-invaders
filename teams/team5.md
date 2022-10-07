# Team5

요청 
1. 게임 메인 화면
    - save파일을 불러와 저장한 게임을 이어서 진행할 수 있는 continue 버튼 필요 (4조에게 요청)
    - 새로운 게임을 시작할 수 있는 new game 버튼 필요 (4조에게 요청)

2. 스테이지 클리어 이후
    - 바로 다음 스테이지로 안 넘어가고 화면을 일시정지 시킨 후 2개의 버튼을 출력  (2조에게 요청)
    - 이어서 다음 레벨로 갈 수 있는 next stage 버튼
    - 게임을 저장하고 메인 화면으로 돌아가는 save&exit 버튼
    - 화면에 띄워진 버튼 두 개 중 하나를 클릭하여 진행

구현 
1. 스테이지 클리어 이후
    - save&exit 선택 - 게임 정보를 파일에 저장 후 메인 화면으로 돌아감

2. 게임 메인 화면
    - continue 선택 - save 해 둔 파일을 불러와서 저장 한 시점부터 게임이 시작

3. 게임 오버할 경우
    - new game시작했으면 게임 오버 시 그냥 end
    continue로 save 파일을 불러와서 게임을 시작 했으면 게임 오버 시 기존 save파일 삭제 후 메인 화면으로 이동
    - 신기록을 달성했을 경우 기록 입력 후 TOP10 점수를 가지고 있는 파일에 순서대로 기록 후 메인화면으로 이동

    
Request
1. Main screen
    - You need a continue button to load the save file and continue the saved game
    - Need a new game button to start a new game

2. After clearing the stage
    - Instead of moving on to the next stage, pause the screen and output the two buttons
    - Next stage button that can go to the next level
    - Save & Exit button that Save the game and return to the main screen
    - Click one of the two buttons on the screen to proceed

Implementation
1. After clearing the stage
    - Click on Save & Exit button - Save game information to file and return to main screen

2. Main screen
    - Click on Continue button - The game starts when the saved file is saved and saved

3. Game over
    - If you start a new game, if the game is over, just end.
      If you have started the game by loading the save file in the continue, delete the existing save file and go to the main screen in case of game over 
    - If a new record is achieved, enter the record and record it in order to the     file with the TOP10 score and go to the main screen