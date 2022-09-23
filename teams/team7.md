# Item System Requirements
- Item Properties and Implementation Principles
1. All items that can be acquired are activated and deleted only for n seconds immediately after the acquisition.
2. At the beginning of each stage,
Monsters to drop items on that stage (x% of all monsters and UFO) are pre-designated randomly.
Items to be dropped are similarly randomly designated in advance, but the order of the items to be dropped is set.
3. Items that are in effect will disappear if any of the following conditions are met.
1. The player clears the stage.
2. The player's life decreases.
3. Item duration time is over.
4. Obtain a new item.
4. If an enemy with an item is destroyed, drop the item from the location to the enemy's bullet path with the enemy's bullet speed k times.
5. Items that are being dropped are marked with a question mark icon, and the player cannot know until they are acquired.
6. If the item and the player collide, the item is acquired.
Items that have not been acquired are deleted when they go down to the bottom of the screen.
7. Once the item is acquired, the description of the item and the remaining effect duration are displayed at the top of the game screen (near the life mark position).
8. Item effects can be added or item sounds can be added.


- 아이템 속성 및 구현 원리
1. 획득할 수 있는 모든 아이템은 획득 직후 n초간만 활성화되고 삭제된다.
2. 매 스테이지가 시작되면, 
해당 스테이지에서 아이템을 드랍할 몬스터는(전체 몬스터의 x%와 UFO) 미리 무작위로 지정된다.
드랍될 아이템은 마찬가지로 미리 무작위로 지정하지만, 아이템의 드랍 순서는 정해져 있다.
3. 효과가 발동중인 아이템은 아래 조건을 하나라도 만족하면 사라진다.
1. 플레이어가 스테이지를 클리어한다. 
2. 플레이어의 라이프가 감소한다. 
3. 아이템지속 시간이 끝난다. 
4. 새로운 아이템을 획득한다.
4. 아이템을 가진 적이 파괴되면, 해당 위치에서 적의 총알 경로로 적의 총알 속도 k배로 아이템을 드랍한다.
5. 드랍중인 아이템은 물음표 아이콘으로 표시되며 획득 전까지 플레이어는 알 수 없다.
6. 아이템과 플레이어가 충돌하면 아이템을 획득한다. 
획득하지 않은 아이템은 화면 밑으로 내려가면 삭제된다.
7. 아이템을 획득하면 아이템의 설명과 남은 효과 지속 시간을 게임 스크린 최상단(라이프 표기 위치 부근)에 띄운다.
8. 아이템 이펙트를 추가하거나 아이템 사운드를 추가할 수 있다.
