package dcdmod.Patches;

import java.io.IOException;

import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import basemod.BaseMod;
import basemod.interfaces.StartGameSubscriber;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Actions.FaizGearAction;
import dcdmod.Actions.TurnTimer;

/**
 * @author 彼君不触
 * @version 9/10/2018
 * @since 9/10/2018
 */

@SpireInitializer
public class ModBaseClassForSLExample extends AbstractSaveLoadSubscriber implements StartGameSubscriber {
	public static void initialize() {
		BaseMod.subscribe(new ModBaseClassForSLExample());
	}

	public SpireConfig config;
	
	@Override
	public void receiveStartGame() {
		if (config == null) {
			try {
				config = new SpireConfig("DCDmod", "Decade");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.setConfig(config);
		// 请使用自己mod的存档配置，记得更改
		
		System.out.println("开始/继续游戏");
		System.out.println("onLoadGame触发前，testOutput = " + testOutput);
		// 在游戏开始或继续时且在onLoadGame触发之前的代码
		super.receiveStartGame();
		// 在游戏开始或继续时且在onLoadGame触发之后的代码
		System.out.println("onLoadGame触发后，testOutput = " + testOutput);
		System.out.println("开始/继续游戏。");
	}

	public static int testOutput = 0;
	public static boolean timevent = false;
	public static boolean TimeVentUpgraded = true;
	public static boolean FaizGear1 = true;
	public static boolean FaizGear2 = true;
	public static boolean FaizGear3 = true;
	public static boolean FaizGear4 = true;
	public static int FaizPoint = 0;
	
	@Override
	protected void onLoadGame() {
		// 在这里写SL时要执行的内容
		TurnTimer.atEndOfRound();
		EnterButtonAction.FaizPhone = FaizGear1 ;
		EnterButtonAction.FaizPointer = FaizGear2;
		EnterButtonAction.FaizShot = FaizGear3;
		EnterButtonAction.FaizEdge = FaizGear4;
		FaizGearAction.FaizPoint = FaizPoint;
		if(timevent) {
				++testOutput;
		}
		else {
			testOutput = 0;
		}
		System.out.println("本次启动游戏程序开始，累计第" + testOutput + "次SL");
	}
}
