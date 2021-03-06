package ai.den_of_evil;

import l2p.gameserver.ai.DefaultAI;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.World;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.serverpackets.components.NpcString;
import l2p.gameserver.scripts.Functions;
import l2p.gameserver.taskmanager.AiTaskManager;

/**
 * @author VISTALL
 * @date 19:24/28.08.2011 Npc Id: 32026 Кричит в чат - если лвл ниже чем 37
 * включно
 */
public class HestuiGuard extends DefaultAI {

    public HestuiGuard(NpcInstance actor) {
        super(actor);

    }

    @Override
    public synchronized void startAITask() {
        if (_aiTask == null) {
            _aiTask = AiTaskManager.getInstance().scheduleAtFixedRate(this, 10000L, 10000L);
        }
    }

    @Override
    protected synchronized void switchAITask(long NEW_DELAY) {
    }

    @Override
    protected boolean thinkActive() {
        NpcInstance actor = getActor();

        for (Player player : World.getAroundPlayers(actor)) {
            if (player.getLevel() <= 37) {
                Functions.npcSay(actor, NpcString.THIS_PLACE_IS_DANGEROUS_S1, player.getName());
            }
        }

        return false;
    }
}
