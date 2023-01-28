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
                    // Calls start game, that returns boolean if it returns true game is already started,
                    // and player can use item, else it starts the game and doesn't use item
                    if (!inputManager.startGame()) {
                        if (inputManager.useItem()) {
                            controller.startVibration(100, 0.2f);
                        }
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
        if (Controllers.getCurrent().getButton(Controllers.getCurrent().getMapping().buttonDpadDown)) inputManager.movePlayer(PlayerMoveDir.DOWN, 1f);
        if (Controllers.getCurrent().getButton(Controllers.getCurrent().getMapping().buttonDpadLeft)) inputManager.movePlayer(PlayerMoveDir.LEFT, 1f);
        if (Controllers.getCurrent().getButton(Controllers.getCurrent().getMapping().buttonDpadUp)) inputManager.movePlayer(PlayerMoveDir.UP, 1f);
        if (Controllers.getCurrent().getButton(Controllers.getCurrent().getMapping().buttonDpadRight)) inputManager.movePlayer(PlayerMoveDir.RIGHT, 1f);
    }
}
