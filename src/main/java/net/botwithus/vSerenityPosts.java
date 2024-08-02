package net.botwithus;

import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.internal.scripts.ScriptDefinition;
import net.botwithus.rs3.game.Client;
import net.botwithus.rs3.game.actionbar.ActionBar;
import net.botwithus.rs3.game.hud.interfaces.Component;
import net.botwithus.rs3.game.hud.interfaces.Interfaces;
import net.botwithus.rs3.game.minimenu.MiniMenu;
import net.botwithus.rs3.game.minimenu.actions.ComponentAction;
import net.botwithus.rs3.game.queries.builders.characters.NpcQuery;
import net.botwithus.rs3.game.queries.builders.components.ComponentQuery;
import net.botwithus.rs3.game.queries.builders.objects.SceneObjectQuery;
import net.botwithus.rs3.game.scene.entities.characters.npc.Npc;
import net.botwithus.rs3.game.scene.entities.characters.player.LocalPlayer;
import net.botwithus.rs3.game.scene.entities.object.SceneObject;
import net.botwithus.rs3.script.Execution;
import net.botwithus.rs3.script.LoopingScript;
import net.botwithus.rs3.script.config.ScriptConfig;
import net.botwithus.rs3.game.Item;
import net.botwithus.rs3.game.queries.builders.items.InventoryItemQuery;
import net.botwithus.api.game.hud.inventories.Backpack;
import net.botwithus.api.game.hud.inventories.Bank;
import net.botwithus.rs3.events.impl.ChatMessageEvent;
import net.botwithus.rs3.events.impl.ServerTickedEvent;
import net.botwithus.rs3.game.Area;
import net.botwithus.rs3.game.Coordinate;
import net.botwithus.rs3.game.queries.results.EntityResultSet;
import net.botwithus.rs3.game.queries.results.ResultSet;
import net.botwithus.rs3.game.skills.Skills;
import net.botwithus.rs3.util.Regex;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class vSerenityPosts extends LoopingScript {

    private BotState botState = BotState.IDLE;
    public int LadyHefinCurrentAnim = 0;
    private Random random = new Random();

  
    enum BotState {
        IDLE,
        START
    }

    private int previousAnimationId = -1; // Add this line to store the previous animation ID

    public vSerenityPosts(String s, ScriptConfig scriptConfig, ScriptDefinition scriptDefinition) {
        super(s, scriptConfig, scriptDefinition);
        this.sgc = new vSerenityPostsGraphicsContext(getConsole(), this);

    }


    public boolean initialize() {
        super.initialize();
        subscribe(ChatMessageEvent.class, chatMessageEvent -> {
        if (chatMessageEvent.getMessage().contains("You don't have the necessary")) {
            println("Out of resources. Stopping script.");
            setBotState(BotState.IDLE);
        }
        });
        return true;
    }

    @Override
    public void onLoop() {
        //Loops every 100ms by default, to change:
        //this.loopDelay = 500;
        LocalPlayer player = Client.getLocalPlayer();
        if (player == null || Client.getGameState() != Client.GameState.LOGGED_IN || botState == BotState.IDLE) {
            //wait some time so we dont immediately start on login.
            Execution.delay(random.nextLong(3000,7000));
            return;
        }
        switch (botState) {
            case IDLE -> {
                //do nothing
                println("We're idle!");
                Execution.delay(random.nextLong(1000,3000));
            }
            case START -> {
                handleLadyHefinAnimation();
                Execution.delay(random.nextLong(450,900));
            }

        }
    }

  

    public void LadyHefinAnimation() {
        Npc LadyHefin = NpcQuery.newQuery().name("Lady Hefin").results().first();
        int animationId = LadyHefin.getAnimationId();
        if (LadyHefin != null) {
            if (animationId == 24538) {
                return; // Skip processing for animation ID 24538
            }
            if (animationId != previousAnimationId) { 
                println("Lady Hefin's animation ID: " + animationId);
                previousAnimationId = animationId; 
            }
            LadyHefinCurrentAnim = animationId; 
        } 
        Execution.delay(random.nextLong(450,900));
    }

    public void handleLadyHefinAnimation() {
        LadyHefinAnimation();
        int playerCurrentAnim = Client.getLocalPlayer().getAnimationId(); // Get the player's current animation

        if (LadyHefinCurrentAnim == 24532 && playerCurrentAnim != 24532) {
            println("Lady Hefin is performing animation crane.");
            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 101711880);
            println("Set players animation to crane.");
            Execution.delay(random.nextLong(450,900));
        }
        if (LadyHefinCurrentAnim == 24535 && playerCurrentAnim != 24535) {
            println("Lady Hefin is performing animation bow.");
            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 101711889);
            println("Set players animation to bow.");
            Execution.delay(random.nextLong(450,900));
        }
        if (LadyHefinCurrentAnim == 24537 && playerCurrentAnim != 24537) {
            println("Lady Hefin is performing animation lotus.");
            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 101711907);
            println("Set players animation to lotus.");
            Execution.delay(random.nextLong(450,900));
        }
        if (LadyHefinCurrentAnim == 24539 && playerCurrentAnim != 24539) {
            println("Lady Hefin is performing animation ward.");
            MiniMenu.interact(ComponentAction.COMPONENT.getType(), 1, -1, 101711898);
            println("Set players animation to ward.");
            Execution.delay(random.nextLong(450,900));
        }
    }

    
    public BotState getBotState() {
        return botState;
    }

    public void setBotState(BotState botState) {
        this.botState = botState;
    }



}