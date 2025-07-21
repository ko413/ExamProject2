import java.util.ArrayList;
public class GameMaster {
    public static void main(String[] args) {
        ArrayList<Character> party = new ArrayList<Character>();
        party.add(new Hero("勇者", 100, "剣"));
        party.add(new Wizard("魔法使い", 60, 20));
        party.add(new Thief("盗賊", 70));
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        monsters.add(new Matango('A', 45));
        monsters.add(new Goblin('A', 50));
        monsters.add(new Slime('A', 40));
        System.out.println("---味方パーティ---");
        for(Character character : party) {
            character.showStatus();
        }
        System.out.println("---敵グループ---");
        for(Monster monster : monsters) {
            monster.showStatus();
        }
        System.out.println("味方の総攻撃！");
        for(Character character : party) {
            for(Monster monster : monsters) {
                character.attack(monster);
            }
        }
        System.out.println("敵の総攻撃！");
        for(Monster monster : monsters) {
            for(Character character : party) {
                monster.attack(character);
            }
        }
        System.out.println("ダメージを受けた勇者が突然光出した！");
        System.out.println("勇者はスーパーヒーローに進化した！");
        Hero hero = (Hero) party.get(0);
        SuperHero superHero = new SuperHero(hero);
        party.set(0, superHero);
        for(Monster monster : monsters) {
            superHero.attack(monster);
        }
        System.out.println("---味方パーティ最終ステータス---");
        for(Character character : party) {
            character.showStatus();
            if(character.isAlive()) {
                System.out.println("生存状況：生存");
            }else {
                System.out.println("生存状況：戦闘不能");
            }
        }
        System.out.println("---敵グループ最終ステータス---");
        for(Monster monster : monsters) {
            monster.showStatus();
            if(monster.isAlive()) {
                System.out.println("生存状況：生存");
            }else {
                System.out.println("生存状況：討伐済み");
            }
        }
    }
}