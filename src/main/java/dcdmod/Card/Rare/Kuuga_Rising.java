package dcdmod.Card.Rare;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import dcdmod.DCDmod;
import dcdmod.Actions.RemoveFormRideAction;
import dcdmod.Card.Special.PegasusAttack;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.RisingDragonPower;
import dcdmod.Power.RisingMightyPower;
import dcdmod.Power.RisingPegasusPower;
import dcdmod.Power.RisingTitanPower;
import dcdmod.Power.SuperRegenPower;



public class Kuuga_Rising extends AbstractCustomCardWithType{
	
	public static final String ID = "Kuuga_Rising";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Kuuga_Rising.png";
	private static final int COST = 3;
	int x;
	
	
	public Kuuga_Rising() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.SKILL, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.RARE, AbstractCard.CardTarget.SELF,CardColorType.Kuuga);
		this.tags.add(DCDmod.RiderCard);
		this.tags.add(DCDmod.KamenRide);
		exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new SFXAction("THUNDERCLAP", 0.05F));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(p.drawX, p.drawY), 0.05F));
		if(p.hasPower("KuugaDragonPower")) {
			x = p.getPower("KuugaDragonPower").amount;
			AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(p, p));
			if(x>0) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RisingDragonPower(p,x),x));
			}
			else {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RisingDragonPower(p,0),0));
			}
		}
		else if(p.hasPower("KuugaPegasusPower")) {
			AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(p, p));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RisingPegasusPower(p,3),3));
			AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new PegasusAttack(), 1));
		}
		else if(p.hasPower("KuugaTitanPower")) {			
			x = p.getPower("KuugaTitanPower").amount;
			AbstractDungeon.actionManager.addToBottom(new RemoveFormRideAction(p, p));
			if(x>0) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RisingTitanPower(p,x),x));
			}
			else {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RisingTitanPower(p,0),0));
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BarricadePower(p),1));
		}
		else {
			if(!p.hasPower("RisingMightyPower")){
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new RisingMightyPower(p,1),1));
			}
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SuperRegenPower(p, 3), 3));
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 2), 2));
		}
		
	}
	
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		boolean canUse = super.canUse(p, m);
		if(!canUse) return false;
		if(!p.hasPower("KamenRideKuugaPower")) {
			canUse = false;
			this.cantUseMessage = EXTENDED_DESCRIPTION[0];
		}
		return canUse;
	}
	
	
	@Override
    public AbstractCard makeCopy() {
        return new Kuuga_Rising();
    }
	
	@Override
	public void optionDecade() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionKuuga() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionAgito() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionRyuki() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionFaiz() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionBlade() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionHibiki() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionKabuto() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionDenO() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionKiva() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void optionNeutral() {

	}
    
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            exhaust = false;
            this.rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Kuuga_Rising");
        NAME = Kuuga_Rising.cardStrings.NAME;
        DESCRIPTION = Kuuga_Rising.cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = Kuuga_Rising.cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = Kuuga_Rising.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.RARE[0], DCDmod.RARE_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.RARE[6], DCDmod.RARE_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.RARE[cost], DCDmod.RARE_P[cost]);
		}
	}

	
	
}
