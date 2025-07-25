import creature.Character;
import creature.Creature;
import creature.Monster;
import creature.character.*;
import creature.monster.*;
import weapon.Dagger;
import weapon.Sword;
import weapon.Wand;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameMaster {
    public static void main(String[] args) {

        // =======================================
        // 1. パーティとモンスターの準備
        // =======================================
        ArrayList<Character> party = new ArrayList<>();
        // 仕様に合わせてキャラクターを作成します
        party.add(new Hero("勇者", 120));
        party.add(new Wizard("魔法使い", 80, 25));
        party.add(new Thief("盗賊", 90));

        ArrayList<Monster> monsters = new ArrayList<>();
        Random rand = new Random();
        int matangoCount = 0;
        int goblinCount = 0;
        int slimeCount = 0;

        for (int i = 0; i < 5; i++) {
            int monsterType = rand.nextInt(3);
            switch (monsterType) {
                case 0:
                    monsters.add(new Matango((char) ('A' + matangoCount++), 45));
                    break;
                case 1:
                    monsters.add(new Goblin((char) ('A' + goblinCount++), 60));
                    break;
                case 2:
                    monsters.add(new Slime((char) ('A' + slimeCount++), 30));
                    break;
            }
        }

        // =======================================
        // 2. メインの戦闘ループ
        // =======================================
        int turn = 1;
        while (!party.isEmpty() && !monsters.isEmpty()) {
            System.out.println("\n----- " + turn + "ターン目 -----");

            // --- ステータス表示 ---
            System.out.println("【味方パーティ】");
            for (Character c : party) {
                c.showStatus();
            }
            System.out.println("\n【敵グループ】");
            for (Monster m : monsters) {
                m.showStatus();
            }
            System.out.println();

            // =======================================
            // 3. 味方の行動フェーズ
            // =======================================
            for (int i = 0; i < party.size(); i++) {
                if (monsters.isEmpty()) break;

                Character currentCharacter = party.get(i);
                if (!currentCharacter.isAlive()) continue;

                System.out.println("★" + currentCharacter.getName() + "のターン");

                try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                    boolean actionCompleted = false;
                    while (!actionCompleted) {
                        try {
                            if (currentCharacter instanceof SuperHero) {
                                System.out.println("スーパーヒーローは攻撃するしかない！");
                            } else if (currentCharacter instanceof Hero) {
                                System.out.print("どうする？ [1: 攻撃, 2: スーパーヒーローになる] > ");
                            } else if (currentCharacter instanceof Wizard) {
                                System.out.print("どうする？ [1: 攻撃(石), 2: 魔法攻撃] > ");
                            } else if (currentCharacter instanceof Thief) {
                                System.out.print("どうする？ [1: 攻撃, 2: 守る] > ");
                            }

                            int actionChoice = 1;
                            if (!(currentCharacter instanceof SuperHero)) {
                                String input = br.readLine();
                                actionChoice = Integer.parseInt(input);
                            }

                            if (currentCharacter instanceof Thief && actionChoice == 2) {
                                ((Thief) currentCharacter).guard();
                                actionCompleted = true;
                                continue;
                            }
                            if (currentCharacter instanceof Hero && !(currentCharacter instanceof SuperHero) && actionChoice == 2) {
                                SuperHero sh = new SuperHero(currentCharacter.getName(), currentCharacter.getHp(), currentCharacter.getWeapon());
                                party.set(i, sh);
                                if (!sh.isAlive()) {
                                    sh.die();
                                    party.remove(i);
                                    i--;
                                }
                                actionCompleted = true;
                                continue;
                            }

                            System.out.println("誰に攻撃する？");
                            for (int j = 0; j < monsters.size(); j++) {
                                System.out.println("[" + j + "]: " + monsters.get(j).getName() + " (HP: " + monsters.get(j).getHp() + ")");
                            }
                            System.out.print("番号を入力 > ");
                            String input = br.readLine();
                            int targetIndex = Integer.parseInt(input);

                            if (targetIndex < 0 || targetIndex >= monsters.size()) {
                                System.out.println("正しい番号を入力してください。");
                                continue;
                            }
                            Monster target = monsters.get(targetIndex);

                            if (currentCharacter instanceof Wizard && actionChoice == 2) {
                                ((Wizard) currentCharacter).magic(target);
                            } else {
                                currentCharacter.attack(target);
                            }

                            Iterator<Monster> monsterIterator = monsters.iterator();
                            while (monsterIterator.hasNext()) {
                                Monster m = monsterIterator.next();
                                if (!m.isAlive()) {
                                    m.die();
                                    monsterIterator.remove();
                                } else if (m.getHp() <= 5) {
                                    m.run();
                                    monsterIterator.remove();
                                }
                            }
                            actionCompleted = true;

                        } catch (NumberFormatException e) {
                            System.out.println("エラー：数値を入力してください。");
                        } catch (Exception e) {
                            System.out.println("予期せぬエラーです：" + e.getMessage());
                        }
                    }
                } catch (Exception e) {
                    System.out.println("入力処理でエラーが発生しました。");
                }
                System.out.println();
            }

            // =======================================
            // 4. 敵の行動フェーズ
            // =======================================
            if (monsters.isEmpty() || party.isEmpty()) break;

            System.out.println("--- 敵のターン ---");
            for (Monster m : monsters) {
                if (party.isEmpty()) break;

                Character target = party.get(rand.nextInt(party.size()));
                m.attack(target);

                if (!target.isAlive()) {
                    target.die();
                }
            }
            party.removeIf(c -> !c.isAlive());

            turn++;
        }

        // =======================================
        // 5. 戦闘結果の表示
        // =======================================
        System.out.println("\n----- 戦闘終了 -----");
        if (monsters.isEmpty()) {
            System.out.println("敵を全て倒した！" + party.get(0).getName() + "達は勝利した！");
        } else {
            System.out.println("味方パーティは全滅してしまった…");
        }
    }
}