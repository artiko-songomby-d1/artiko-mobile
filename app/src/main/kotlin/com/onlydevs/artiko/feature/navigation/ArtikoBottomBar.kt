package com.onlydevs.artiko.feature.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onlydevs.artiko.ui.theme.ArtikoText
import com.onlydevs.artiko.ui.theme.ArtikoTheme

private val BAR_MARGIN = 12.dp
private val BAR_CORNER = 16.dp
private val BAR_PADDING = 6.dp
private val BAR_ELEVATION = 12.dp
private val BORDER_WIDTH = 1.dp
private val ITEM_CORNER = 8.dp
private val ITEM_HORIZONTAL_PADDING = 14.dp
private val ITEM_VERTICAL_PADDING = 6.dp
private val ICON_SIZE = 22.dp
private const val GLASS_ALPHA = 0.85f
private const val BORDER_ALPHA = 0.08f

/** The floating glass bar of artiko-web; the open tab is written in italic serif and red. */
@Composable
fun ArtikoBottomBar(selectedTab: TopLevelTab?, onTabSelected: (TopLevelTab) -> Unit) {
    Surface(
        modifier = Modifier.navigationBarsPadding().padding(BAR_MARGIN),
        shape = RoundedCornerShape(BAR_CORNER),
        color = Color.White.copy(alpha = GLASS_ALPHA),
        border = BorderStroke(BORDER_WIDTH, MaterialTheme.colorScheme.onBackground.copy(alpha = BORDER_ALPHA)),
        shadowElevation = BAR_ELEVATION,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(BAR_PADDING),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            TopLevelTab.entries.forEach { tab ->
                BarItem(tab = tab, selected = tab == selectedTab, onClick = { onTabSelected(tab) })
            }
        }
    }
}

@Composable
private fun BarItem(tab: TopLevelTab, selected: Boolean, onClick: () -> Unit) {
    val color = if (selected) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onBackground
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(ITEM_CORNER))
            .clickable(onClick = onClick)
            .padding(horizontal = ITEM_HORIZONTAL_PADDING, vertical = ITEM_VERTICAL_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(imageVector = tab.icon, contentDescription = null, tint = color, modifier = Modifier.size(ICON_SIZE))
        Text(
            text = tab.label,
            style = if (selected) ArtikoText.activeTab else MaterialTheme.typography.labelMedium,
            color = color,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ArtikoBottomBarPreview() {
    ArtikoTheme {
        ArtikoBottomBar(selectedTab = TopLevelTab.Discovery, onTabSelected = {})
    }
}
