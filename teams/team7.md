# Item System Implementation Proposal
Requirements : Item System

- Enemies drop items such as attack, shield, points, etc where players pick them up

Details
- Add Items
  - List of items to be added

|Item name|Description(lasts for n seconds)|
|------|---|
|Shield|The player becomes invincible.|
|Extra life|Add player's life.|
|Increasing player’s speed|Increase the player’s movement speed by k times.|
|Decreasing the enemy’s speed|Decrease the enemy’s movement speed by k times.|
|Increasing the speed of the bullet|Increase the player’s bullet speed by k times.|
|Increasing the points given by monsters|Get n times points when the player destroys the enemy.|

   - Item Properties and Implementation
1. **All items players can equip are activated only for n seconds and deleted immediately.**
2. **When every stage starts, The system randomly determines which monster(x% of all monsters and UFO) to drop the item. The types of items are also randomly determined, but the sequence of dropping items are fixed.**
3. **The effect of the item disappears if any of the following conditions are satisfied.**
    1. The player clears the stage.
    2. The player's life decreases. 
    3. The item duration time is over. 
    4. Get a new item.
4. **The item is dropped from where the enemy is killed. 
The speed of the item being dropped is k times the speed of the bullet fired by the enemy.**
5. **The item is indicated by a question mark. The player cannot know until they get it.**
6. **When the player contacts the item, he acquires it. The player may acquire an item before it is off the screen.**
7. **Item description and duration are displayed at the top of the game screen (near the life mark position).**
8. **Item effects or sounds can be added.**
