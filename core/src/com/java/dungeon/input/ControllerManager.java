package com.java.dungeon.input;

import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.Controllers;

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
                    if (inputManager.useItem()) {
                        controller.startVibration(200, 0.4f);
                    }
                }
                if (buttonCode == controller.getMapping().buttonStart) {
                    inputManager.pause();
                }
                return false;
            }

            @Override
            public boolean buttonUp(Controller controller, int buttonCode) {
                return false;
            }

            @Override
            public boolean axisMoved(Controller controller, int axisCode, float value) {
                return false;
            }
        };
    }

    public static void checkAxis(InputManager inputManager) {
        float CONTROLLER_DEADZONE = 0.1f;

        float value = Controllers.getCurrent().getAxis(1);
        if (value < -CONTROLLER_DEADZONE) {
            inputManager.movePlayer(PlayerMoveDir.UP, -value);
        } else if (value > CONTROLLER_DEADZONE) inputManager.movePlayer(PlayerMoveDir.DOWN, value);


        value = Controllers.getCurrent().getAxis(0);
        if (value < -CONTROLLER_DEADZONE) {
            inputManager.movePlayer(PlayerMoveDir.LEFT, -value);
        } else if (value > CONTROLLER_DEADZONE) inputManager.movePlayer(PlayerMoveDir.RIGHT, value);
    }
    public static void checkDpad(InputManager inputManager) {
        System.out.println(Controllers.getCurrent().getMapping().buttonDpadLeft);
    }
}
