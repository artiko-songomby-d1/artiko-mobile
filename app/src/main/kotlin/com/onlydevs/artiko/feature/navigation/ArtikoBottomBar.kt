package com.onlydevs.artiko.feature.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.onlydevs.artiko.ui.theme.ArtikoTheme

@Composable
fun ArtikoBottomBar(selectedTab: TopLevelTab?, onTabSelected: (TopLevelTab) -> Unit) {
    NavigationBar {
        TopLevelTab.entries.forEach { tab ->
            NavigationBarItem(
                selected = tab == selectedTab,
                onClick = { onTabSelected(tab) },
                icon = { Icon(imageVector = tab.icon, contentDescription = null) },
                label = { Text(tab.label) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ArtikoBottomBarPreview() {
    ArtikoTheme {
        ArtikoBottomBar(selectedTab = TopLevelTab.Discovery, onTabSelected = {})
    }
}
