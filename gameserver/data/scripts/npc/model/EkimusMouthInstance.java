package npc.model;

import l2p.gameserver.instancemanager.SoIManager;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.templates.npc.NpcTemplate;
import l2p.gameserver.utils.ReflectionUtils;
import instances.ErosionHallAttack;
import instances.ErosionHallDefence;
import instances.SufferingHallAttack;
import instances.SufferingHallDefence;

/**
 * @author pchayka
 */
public final class EkimusMouthInstance extends NpcInstance {

    private static final int hosattackIzId = 115;
    private static final int hoeattackIzId = 119;

    private static final int hosdefenceIzId = 116;
    private static final int hoedefenceIzId = 120;

    public EkimusMouthInstance(int objectId, NpcTemplate template) {
        super(objectId, template);
    }

    @Override
    public void onBypassFeedback(Player player, String command) {
        if (!canBypassCheck(player, this)) {
            return;
        }

        if (command.equalsIgnoreCase("hos_enter")) {
            if (SoIManager.getCurrentStage() == 1) {
                ReflectionUtils.simpleEnterInstancedZone(player, SufferingHallAttack.class, hosattackIzId);
            } else if (SoIManager.getCurrentStage() == 4) {
                ReflectionUtils.simpleEnterInstancedZone(player, SufferingHallDefence.class, hosdefenceIzId);
            }
        } else if (command.equalsIgnoreCase("hoe_enter")) {
            if (SoIManager.getCurrentStage() == 1) {
                ReflectionUtils.simpleEnterInstancedZone(player, ErosionHallAttack.class, hoeattackIzId);
            } else if (SoIManager.getCurrentStage() == 4) {
                ReflectionUtils.simpleEnterInstancedZone(player, ErosionHallDefence.class, hoedefenceIzId);
            }
        } else {
            super.onBypassFeedback(player, command);
        }
    }
}
