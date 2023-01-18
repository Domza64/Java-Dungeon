package com.java.dungeon.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;

public class ControllerManager {
    InputManager inputManager;

    public ControllerManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public ControllerListener getListener() {
        return new ControllerListener() {
            @Override
            public void connected(Controller controller) {
                inputManager.controllerConnected();
            }

            @Override
            public void disconnected(Controller controller) {
                inputManager.controllerDisconnected();
            }

            @Override
            public boolean buttonDown(Controller controller, int buttonCode) {
                if (buttonCode == controller.getMapping().buttonA) {
                    inputManager.useItem();
                }
                return false;
            }

            @Override
            public boolean buttonUp(Controller controller, int buttonCode) {
                return false;
            }

            @Override
            public boolean axisMoved(Controller controller, int axisCode, float value) {
                float CONTROLLER_DEADZONE = 0.1f;
                if (axisCode == 1) {
                    if (value < -CONTROLLER_DEADZONE) {
                        inputManager.movePlayer(PlayerMoveDir.UP);
                    } else if (value > CONTROLLER_DEADZONE) {
                        inputManager.movePlayer(PlayerMoveDir.DOWN);
                    }
                }
                if (axisCode == 0) {
                    if (value < -CONTROLLER_DEADZONE) {
                        inputManager.movePlayer(PlayerMoveDir.LEFT);
                    } else if (value > CONTROLLER_DEADZONE) {
                        inputManager.movePlayer(PlayerMoveDir.RIGHT);
                    }
                }
                return false;
            }
        };
    }
}
