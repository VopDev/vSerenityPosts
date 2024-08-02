package net.botwithus;


import net.botwithus.vSerenityPosts.BotState;
import net.botwithus.rs3.imgui.ImGui;
import net.botwithus.rs3.imgui.ImGuiWindowFlag;
import net.botwithus.rs3.script.ScriptConsole;
import net.botwithus.rs3.script.ScriptGraphicsContext;

public class vSerenityPostsGraphicsContext extends ScriptGraphicsContext {

    private vSerenityPosts script;

    public vSerenityPostsGraphicsContext(ScriptConsole scriptConsole, vSerenityPosts script) {
        super(scriptConsole);
        this.script = script;
    }

    

    @Override
    public void drawSettings() {
        if (ImGui.Begin("vSerenityPosts", ImGuiWindowFlag.None.getValue())) {
            if (ImGui.BeginTabBar("vSerenityPosts", ImGuiWindowFlag.None.getValue())) {
                if (ImGui.BeginTabItem("Info", ImGuiWindowFlag.None.getValue())) {

                    ImGui.Text("Begin the script already on the Serenity post.");
                    ImGui.Text("");
                    if (ImGui.Button("Start")) {
                        script.setBotState(BotState.START);
                    }
                    ImGui.SameLine();
                    if (ImGui.Button("Stop")) {
                        script.setBotState(BotState.IDLE);
                    }
                    ImGui.EndTabItem();
                  
                }
               
                   
                    
            };
            
                   
               
                
            }
            ImGui.End(); // Close window
        }
    

    @Override
    public void drawOverlay() {
        super.drawOverlay();
    }
}