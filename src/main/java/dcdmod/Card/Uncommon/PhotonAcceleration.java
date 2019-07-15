package dcdmod.Card.Uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.PhotonAccelerationPower;



public class PhotonAcceleration extends AbstractCustomCardWithType{
	
	public static final String ID = "PhotonAcceleration";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/PhotonAcceleration.png";
	private static final int COST = 2;
	
	public PhotonAcceleration() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.POWER, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF,CardColorType.Kabuto);
		this.tags.add(DCDmod.RiderCard);
		this.baseMagicNumber = this.magicNumber = 1;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PhotonAccelerationPower(p, this.magicNumber), this.magicNumber));
		/*boolean hasRelic =false;
		  for(AbstractRelic r1 : p.relics){
			if(r1.relicId == "PhotonAccelerationRelic") {
				hasRelic = true;
			}
		}
		if(!hasRelic) {
			AbstractRelic r = new PhotonAccelerationRelic();
			AbstractDungeon.player.relics.add(r);
		}*/

	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(p.hasPower("PhotonAccelerationPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		return canUse;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new PhotonAcceleration();
    }
	
	@Override
	public void optionDecade() {

	}

	@Override
	public void optionKuuga() {

	}

	@Override
	public void optionAgito() {

	}

	@Override
	public void optionRyuki() {

	}

	@Override
	public void optionFaiz() {

	}

	@Override
	public void optionBlade() {

	}

	@Override
	public void optionHibiki() {

	}

	@Override
	public void optionKabuto() {

	}

	@Override
	public void optionDenO() {

	}

	@Override
	public void optionKiva() {

	}

	@Override
	public void optionNeutral() {

	}
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
            this.isInnate = true;
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("PhotonAcceleration");
        NAME = PhotonAcceleration.cardStrings.NAME;
        DESCRIPTION = PhotonAcceleration.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = PhotonAcceleration.cardStrings.EXTENDED_DESCRIPTION;
    	UPGRADE_DESCRIPTION = PhotonAcceleration.cardStrings.UPGRADE_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.UNCOMMON[0], DCDmod.UNCOMMON_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.UNCOMMON[6], DCDmod.UNCOMMON_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.UNCOMMON[cost], DCDmod.UNCOMMON_P[cost]);
		}
	}

	
	
}
