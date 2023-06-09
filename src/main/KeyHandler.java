package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_SPACE) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                } else if (gp.ui.commandNum == 1) {
                    System.exit(0);
                }
            }
        } else if (gp.gameState == gp.playState) {
            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_SPACE) {
                spacePressed = true;
            }
        } else if (gp.gameState == gp.pauseState) {
            if (gp.ui.battleNum == 0) {
                if (code == KeyEvent.VK_A) {
                    gp.ui.choiceNum--;
                    if (gp.ui.choiceNum < 0) {
                        gp.ui.choiceNum = 1;
                    }
                }
                if (code == KeyEvent.VK_D) {
                    gp.ui.choiceNum++;
                    if (gp.ui.choiceNum > 1) {
                        gp.ui.choiceNum = 0;
                    }
                }
                if (code == KeyEvent.VK_SPACE) {
                    if (gp.ui.choiceNum == 1) {
                        gp.ui.choiceNum = 0;
                        gp.ui.battleNum = 3;
                    } else {
                        gp.ui.choiceNum = 0;
                        gp.ui.battleNum++;
                    }

                }
            } else if (gp.ui.battleNum == 1) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.choiceNum--;
                    if (gp.ui.choiceNum == 1) {
                        gp.ui.choiceNum = 3;
                    }
                    if (gp.ui.choiceNum == -1) {
                        gp.ui.choiceNum = 1;
                    }
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.choiceNum++;
                    if (gp.ui.choiceNum == 2) {
                        gp.ui.choiceNum = 0;
                    }
                    if (gp.ui.choiceNum == 4) {
                        gp.ui.choiceNum = 2;
                    }
                }
                if (code == KeyEvent.VK_A) {
                    gp.ui.choiceNum -= 2;
                    if (gp.ui.choiceNum == -2) {
                        gp.ui.choiceNum = 2;
                    }
                    if (gp.ui.choiceNum == -1) {
                        gp.ui.choiceNum = 3;
                    }
                }
                if (code == KeyEvent.VK_D) {
                    gp.ui.choiceNum += 2;
                    if (gp.ui.choiceNum == 4) {
                        gp.ui.choiceNum = 0;
                    }
                    if (gp.ui.choiceNum == 5) {
                        gp.ui.choiceNum = 1;
                    }
                }
                if (code == KeyEvent.VK_ESCAPE) {
                    gp.ui.choiceNum = 0;
                    gp.ui.battleNum--;
                }
                if (code == KeyEvent.VK_SPACE) {
                    if (gp.player.moves[gp.ui.choiceNum].cost <= gp.player.mana) {
                        gp.ui.battleNum++;
                    }
                }
            } else if (gp.ui.battleNum == 3) {
                if (code == KeyEvent.VK_A) {
                    if (gp.ui.choiceNum == 0) {
                        gp.ui.choiceNum = 1;
                    } else {
                        gp.ui.choiceNum--;
                    }
                }
                if (code == KeyEvent.VK_D) {
                    if (gp.ui.choiceNum == 1) {
                        gp.ui.choiceNum = 0;
                    } else {
                        gp.ui.choiceNum++;
                    }
                }
                if (code == KeyEvent.VK_SPACE) {
                    System.out.println(gp.ui.choiceNum);
                    if (gp.ui.choiceNum == 0 && gp.player.potions >= 1 && gp.player.life != gp.player.maxLife) {
                        gp.ui.battleNum ++;
                    } else if (gp.ui.choiceNum == 1 && gp.player.mpPotions >= 1 && gp.player.mana != gp.player.maxMana) {
                        gp.ui.battleNum ++;
                    }
                }
                if (code == KeyEvent.VK_ESCAPE) {
                    gp.ui.choiceNum = 0;
                    gp.ui.battleNum = 0;
                }
            }
        } else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_SPACE) {
                gp.oldMan.speak();
            }
        } else if (gp.gameState == gp.deathState || gp.gameState == gp.winState) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_SPACE) {
                if (gp.ui.commandNum == 0) {
                    Main.reset();
                } else if (gp.ui.commandNum == 1) {
                    System.exit(0);
                }
            }
        } else if (gp.gameState == gp.tradeState) {
            if (code == KeyEvent.VK_W) {
                if (gp.ui.dialogueNum == -1) {
                    gp.ui.dialogueNum ++;
                } else {
                    gp.ui.dialogueNum--;
                }
                if (gp.ui.dialogueNum < 0) {
                    gp.ui.dialogueNum = 4;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.dialogueNum++;
                if (gp.ui.dialogueNum > 4) {
                    gp.ui.dialogueNum = 0;
                }
            }
            if (code == KeyEvent.VK_SPACE) {
                if (gp.ui.dialogueNum == 0) {
                    if (gp.player.souls >= 200) {
                        gp.player.updateValues(100, 0, 0, 0, 0);
                        gp.player.life += 100;
                        gp.player.souls -= 200;
                        gp.ui.dialogueNum = 0;
                        gp.gameState = gp.playState;
                    }
                } else if (gp.ui.dialogueNum == 1) {
                    if (gp.player.souls >= 200) {
                        gp.player.updateValues(0, 5, 0, 0, 0);
                        gp.player.mana += 5;
                        gp.player.souls -= 200;
                        gp.ui.dialogueNum = 0;
                        gp.gameState = gp.playState;
                    }
                } else if (gp.ui.dialogueNum == 2) {
                    if (gp.player.souls >= 400) {
                        gp.player.updateValues(0, 0, 1, 0, 0);
                        gp.player.souls -= 400;
                        gp.ui.dialogueNum = 0;
                        gp.gameState = gp.playState;
                    }
                } else if (gp.ui.dialogueNum == 3) {
                    if (gp.player.souls >= 100) {
                        gp.player.updateValues(0, 0, 0, 1, 0);
                        gp.player.souls -= 100;
                        gp.ui.dialogueNum = 0;
                        gp.gameState = gp.playState;
                    }
                } else if (gp.ui.dialogueNum == 4) {
                    if (gp.player.souls >= 100) {
                        gp.player.updateValues(0, 0, 0, 0, 1);
                        gp.player.souls -= 100;
                        gp.ui.dialogueNum = 0;
                        gp.gameState = gp.playState;
                    }
                }
            }
            if (code == KeyEvent.VK_ESCAPE) {
                gp.ui.dialogueNum = 0;
                gp.gameState = gp.playState;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
       if (code == KeyEvent.VK_D) {
            rightPressed = false;
       }
    }

    @Override
    public void keyTyped(KeyEvent e) {/* unused */}
}
