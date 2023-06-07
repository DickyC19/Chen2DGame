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
                    gp.ui.choiceNum = 0;
                    gp.ui.battleNum++;
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
                    gp.ui.battleNum++;
                }
            } else if (gp.ui.battleNum == 2) {
                if (code == KeyEvent.VK_SPACE) {
                    gp.ui.waitNum ++;
                }
            }

        } else if (gp.gameState == gp.dialogueState) {
            if (code == KeyEvent.VK_SPACE) {
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
