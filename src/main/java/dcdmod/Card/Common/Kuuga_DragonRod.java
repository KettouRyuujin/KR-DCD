package dcdmod.Card.Common;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.KuugaRollpower;
import dcdmod.Power.KuugaSpecialPower;
import dcdmod.Vfx.Kuuga_DragonAttack;
import java.util.ArrayList;
import java.util.List;


public class Kuuga_DragonRod extends AbstractCustomCardWithType{
	
	public static final String ID = "Kuuga_DragonRod";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Kuuga_DragonRod.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 2;
	private static final int MAGIC_NUM = 3;
	private List<TooltipInfo> tips;
	
	
	public Kuuga_DragonRod() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Kuuga);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[3], EXTENDED_DESCRIPTION[4]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("KuugaDragonPower")||p.hasPower("RisingDragonPower")) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KuugaRollpower(p, 1), 1));
			if(p.hasPower("RisingDragonPower") && m.hasPower("KuugaSpecialPower")&&m.getPower("KuugaSpecialPower").amount>5) {
				this.magicNumber = this.magicNumber + (m.getPower("KuugaSpecialPower").amount/5);
			}
			if(!DCDmod.AnimationTrigger){
				AbstractDungeon.actionManager.addToTop(new VFXAction(new Kuuga_DragonAttack(m,this.damage,this.magicNumber,false,p.drawX,p.drawY), 0F));
			}
			else{
				for(int i=0;i<this.magicNumber;i++) {
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				}
			}
		}
		else{
			for(int i=0;i<this.magicNumber;i++) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			}
		}
		if(p.hasPower("KamenRideKuugaPower")){
			int x = this.magicNumber;
			if(p.hasPower("RisingMightyPower")){
				x = this.magicNumber * 2;
			}
			for(int i=0;i<x;i++) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m,p, new KuugaSpecialPower(m,1), 1));
			}
		}
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
		if(AbstractDungeon.player.hasPower("BladeSlashPower")) {
			this.damage += AbstractDungeon.player.getPower("BladeSlashPower").amount * 2;
			this.isDamageModified = true;
		}
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Kuuga_DragonRod();
    }
	
	@Override
	public void optionDecade() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_DragonRod.png");
        initializeDescription();
	}

	@Override
	public void optionKuuga() {
		this.name = NAME;
		if(AbstractDungeon.player.hasPower("KuugaDragonPower")) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
			loadCardImage("img/cards/Kuuga_DragonRod.png");
		}
		if(AbstractDungeon.player.hasPower("RisingDragonPower")) {
			this.rawDescription = EXTENDED_DESCRIPTION[1];
			this.name = EXTENDED_DESCRIPTION[2];
			loadCardImage("img/cards/Rising_DragonRod.png");
		}
        initializeDescription();
	}

	@Override
	public void optionAgito() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_DragonRod.png");
        initializeDescription();
	}

	@Override
	public void optionRyuki() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_DragonRod.png");
        initializeDescription();
	}

	@Override
	public void optionFaiz() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_DragonRod.png");
        initializeDescription();
	}

	@Override
	public void optionBlade() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_DragonRod.png");
        initializeDescription();
	}

	@Override
	public void optionHibiki() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_DragonRod.png");
        initializeDescription();
	}

	@Override
	public void optionKabuto() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_DragonRod.png");
        initializeDescription();
	}

	@Override
	public void optionDenO() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_DragonRod.png");
        initializeDescription();
	}

	@Override
	public void optionKiva() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_DragonRod.png");
        initializeDescription();
	}

	@Override
	public void optionNeutral() {
		this.name = NAME;
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_DragonRod.png");
        initializeDescription();
	}
    
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Kuuga_DragonRod");
        NAME = Kuuga_DragonRod.cardStrings.NAME;
        DESCRIPTION = Kuuga_DragonRod.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Kuuga_DragonRod.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.COMMON[0], DCDmod.COMMON_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.COMMON[6], DCDmod.COMMON_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.COMMON[cost], DCDmod.COMMON_P[cost]);
		}
	}

	
	
}
